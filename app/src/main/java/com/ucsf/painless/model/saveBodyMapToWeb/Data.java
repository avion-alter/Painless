package com.ucsf.painless.model.saveBodyMapToWeb;

public class Data
{
    private String inrt_id;

    private String pat_id;

    public String getInrt_id ()
    {
        return inrt_id;
    }

    public void setInrt_id (String inrt_id)
    {
        this.inrt_id = inrt_id;
    }

    public String getPat_id ()
    {
        return pat_id;
    }

    public void setPat_id (String pat_id)
    {
        this.pat_id = pat_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [inrt_id = "+inrt_id+", pat_id = "+pat_id+"]";
    }
}
