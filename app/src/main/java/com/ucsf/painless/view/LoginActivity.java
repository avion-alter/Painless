package com.ucsf.painless.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ucsf.painless.R;
import com.ucsf.painless.model.login.LoginResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.FingerprintHandler;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.utils.ValidationUtils;
import com.ucsf.painless.viewModel.LoginViewModel;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private Context mContext;

    private SessionManager sessionManager;
    private KeyStore keyStore;
    // Variable used for storing the key in the Android Keystore container
    private static final String KEY_NAME = "androidHive";
    private Cipher cipher;
    private TextView textView;
    LoginViewModel loginViewModel;
    private ValidationUtils validationUtils;
    @Override
    protected void onDestroy() {
        mContext = null;
        sessionManager = null;
        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(LoginActivity.this);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        mContext = LoginActivity.this;
        validationUtils = new ValidationUtils();
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);


        if (sessionManager.getBooleanKey(SessionKeyConstant.KEY_is_login)) {
            Toast.makeText(mContext, "You can use fingerprint authentication to Login", Toast.LENGTH_SHORT).show();
            fingerprint();
        }
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        //Toast.makeText(mContext, "Test", Toast.LENGTH_SHORT).show();
        if (validate()){
            loginViewModel.getSignIn(edtEmail.getText().toString(), edtPassword.getText().toString(),LoginActivity.this).observe(this, new Observer<LoginResponse>() {
                @Override
                public void onChanged(@Nullable LoginResponse loginResponse) {
                    try {
                        if (loginResponse.isStatus()){
                            sessionManager.setBooleanKey(SessionKeyConstant.KEY_is_login,true);

                            Intent n = new Intent(LoginActivity.this, DashboardActivity.class);
                            n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(n);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, ""+loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){

                        e.printStackTrace();
                    }
                }
            });
        }

    }


    private boolean validate() {

        boolean checkflag = false;

        checkflag = validationUtils.validateForNull(edtEmail, getResources().getString(R.string.usernamerror));
        if (!checkflag) {
            requestFocus(edtEmail);
            return checkflag;
        }

        checkflag = validationUtils.validateEmail(mContext, edtEmail);
        if (!checkflag) {
            requestFocus(edtEmail);
            return checkflag;
        }

        checkflag = validationUtils.validateForNull(edtPassword, getResources().getString(R.string.passerror));
        if (!checkflag) {
            requestFocus(edtPassword);
            return checkflag;
        }

        return checkflag;
    }

    //Request after error
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }

        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }

        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }

        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }


    public void fingerprint(){


        // Initializing both Android Keyguard Manager and Fingerprint Manager
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        FingerprintManager fingerprintManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        }


        // Check whether the device has a Fingerprint sensor.
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(!fingerprintManager.isHardwareDetected()){
                    /**
                     * An error message will be displayed if the device does not contain the fingerprint hardware.
                     * However if you plan to implement a default authentication method,
                     * you can redirect the user to a default authentication activity from here.
                     * Example:
                     * Intent intent = new Intent(this, DefaultAuthenticationActivity.class);
                     * startActivity(intent);
                     */
                    //textView.setText("Your Device does not have a Fingerprint Sensor");
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }else {
                    // Checks whether fingerprint permission is set on manifest
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                        //textView.setText("Fingerprint authentication permission not enabled");
                        Toast.makeText(mContext, "Fingerprint authentication permission not enabled", Toast.LENGTH_SHORT).show();
                    }else{
                        // Check whether at least one fingerprint is registered
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (!fingerprintManager.hasEnrolledFingerprints()) {
                                //textView.setText("Register at least one fingerprint in Settings");
                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                startActivity(intent);
                            }else{
                                // Checks whether lock screen security is enabled or not
                                if (!keyguardManager.isKeyguardSecure()) {
                                    //textView.setText("Lock screen security not enabled in Settings");
                                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                    startActivity(intent);
                                }else{
                                    generateKey();

                                    if (cipherInit()) {
                                        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                                        FingerprintHandler helper = new FingerprintHandler(this);
                                        helper.startAuth(fingerprintManager, cryptoObject);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
    }

}
