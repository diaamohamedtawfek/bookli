
package com.example.bookli.models.Data_locationclincDoctor;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultData {

    @SerializedName("clinicId")
    @Expose
    private Integer clinicId;
    @SerializedName("clinicBranchMobileDtoList")
    @Expose
    private List<ClinicBranchMobileDtoList> clinicBranchMobileDtoList = null;

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    public List<ClinicBranchMobileDtoList> getClinicBranchMobileDtoList() {
        return clinicBranchMobileDtoList;
    }

    public void setClinicBranchMobileDtoList(List<ClinicBranchMobileDtoList> clinicBranchMobileDtoList) {
        this.clinicBranchMobileDtoList = clinicBranchMobileDtoList;
    }

}
