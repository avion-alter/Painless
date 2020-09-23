package com.ucsf.painless.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucsf.painless.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ValidationUtils {

    Context mContext;

    //Check email validation
    public boolean validateEmail(Context context, EditText p_editText) {
        this.mContext = context;
        boolean m_isValid = false;
        String p_nullMsg = context.getString(R.string.email_null_error);
        String p_invalidMsg = context.getString(R.string.email_error);

        try {
            if (p_editText != null) {
                if (validateForNull(p_editText, p_nullMsg)) {
                    Pattern m_pattern = Pattern.compile("([\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Za-z]{2,4})");
                    Matcher m_matcher = m_pattern.matcher(p_editText.getText().toString().trim());
                    if (!m_matcher.matches() && p_editText.getText().toString().trim().length() > 0) {
                        m_isValid = false;
                        p_editText.setError(p_invalidMsg);
                    } else {
                        m_isValid = true;
                    }
                } else {
                    m_isValid = false;
                }
            } else {
                m_isValid = false;
            }
        } catch (Throwable p_e) {
            p_e.printStackTrace(); // Error handling if application crashes
        }
        return m_isValid;
    }

    //Check password validation
    public boolean validatePassword(Context context, EditText p_editText) {
        this.mContext = context;
        String p_nullMsg = context.getString(R.string.password_null_error);
        String p_invalidMsg = context.getString(R.string.password_error);

        boolean m_isValid = false;
        try {
            if (p_editText != null) {
                if (validateForNull(p_editText, p_nullMsg)) {
                    //Pattern m_pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[&?+@#*$-/%]).{6,10})");
                    Pattern m_pattern = Pattern.compile("(().{6,})");

                    Matcher m_matcher = m_pattern.matcher(p_editText.getText().toString().trim());
                    if (!m_matcher.matches() && p_editText.getText().toString().trim().length() > 0) {
                        m_isValid = false;
                        p_editText.setError(p_invalidMsg);
                    } else {
                        m_isValid = true;
                    }

                } else {
                    m_isValid = false;
                }
            } else {
                m_isValid = false;
            }
        } catch (Throwable p_e) {
            p_e.printStackTrace(); // Error handling if application crashes
        }
        return m_isValid;
    }

    public boolean validateForNull(EditText p_editText, String p_nullMsg) {
        boolean m_isValid = false;
        try {
            if (p_editText != null && p_nullMsg != null) {
                if (TextUtils.isEmpty(p_editText.getText().toString().trim())) {
                    p_editText.setError(p_nullMsg);
                    m_isValid = false;
                } else {
                    m_isValid = true;
                }
            }
        } catch (Throwable p_e) {
            p_e.printStackTrace(); // Error handling if application crashes
        }
        return m_isValid;
    }

    public boolean validateForLength(EditText p_editText, String lengthError, int length) {
        boolean m_isValid = false;
        try {
            if (p_editText != null && p_editText.getText().toString().length() > 0) {
                if (p_editText.getText().toString().length() < length) {
                    p_editText.setError(lengthError);
                    m_isValid = false;
                } else {
                    m_isValid = true;
                }
            }
        } catch (Throwable p_e) {
            p_e.printStackTrace(); // Error handling if application crashes
        }
        return m_isValid;
    }

    public boolean validateTextViewForNull(TextView _textView, String p_nullMsg) {
        boolean m_isValid = false;
        try {
            if (_textView != null && p_nullMsg != null) {
                if (TextUtils.isEmpty(_textView.getText().toString().trim())) {
                    _textView.setError(p_nullMsg);
                    m_isValid = false;
                } else {
                    m_isValid = true;
                }
            }
        } catch (Throwable p_e) {
            p_e.printStackTrace(); // Error handling if application crashes
        }
        return m_isValid;
    }

    public boolean validateEqualPassword(EditText p_editText, EditText c_editText, String p_nullMsg) {
        boolean m_isValid = false;
        try {
            if (p_editText != null && c_editText != null && p_nullMsg != null) {
                if (!p_editText.getText().toString().trim().equalsIgnoreCase(c_editText.getText().toString().trim())) {
                    c_editText.setError(p_nullMsg);
                    m_isValid = false;
                } else {
                    m_isValid = true;
                }
            }
        } catch (Throwable p_e) {
            p_e.printStackTrace(); // Error handling if application crashes
        }
        return m_isValid;
    }




    //Social Security Number validation
    public boolean isSSNValid(EditText s_edtText, String s_Message) {
        boolean isValid = false;
 /*SSN format xxx-xx-xxxx, xxxxxxxxx, xxx-xxxxxx; xxxxx-xxxx:
         ^\\d{3}: Starts with three numeric digits.
	[- ]?: Followed by an optional "-"
	\\d{2}: Two numeric digits after the optional "-"
	[- ]?: May contain an optional second "-" character.
	\\d{4}: ends with four numeric digits.
        Examples: 879-89-8989; 869878789 etc.
*/
//Initialize regex for SSN.
        String expression = "^\\d{3}[- ]?\\d{2}[- ]?\\d{4}$";
        CharSequence inputStr = s_edtText.getText().toString().trim();
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        } else {
            s_edtText.setError(s_Message);
            isValid = false;
        }
        return isValid;
    }


}
