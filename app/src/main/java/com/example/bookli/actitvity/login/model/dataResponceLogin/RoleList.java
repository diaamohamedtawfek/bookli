
package com.example.bookli.actitvity.login.model.dataResponceLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoleList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdById")
    @Expose
    private Object createdById;
    @SerializedName("createdDate")
    @Expose
    private Object createdDate;
    @SerializedName("lastModifiedById")
    @Expose
    private Object lastModifiedById;
    @SerializedName("lastModifiedDate")
    @Expose
    private Object lastModifiedDate;
    @SerializedName("roleName")
    @Expose
    private String roleName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("securityUserRoleList")
    @Expose
    private Object securityUserRoleList;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RoleList() {
    }

    /**
     * 
     * @param createdDate
     * @param lastModifiedDate
     * @param lastModifiedById
     * @param roleName
     * @param securityUserRoleList
     * @param description
     * @param id
     * @param createdById
     */
    public RoleList(Integer id, Object createdById, Object createdDate, Object lastModifiedById, Object lastModifiedDate, String roleName, String description, Object securityUserRoleList) {
        super();
        this.id = id;
        this.createdById = createdById;
        this.createdDate = createdDate;
        this.lastModifiedById = lastModifiedById;
        this.lastModifiedDate = lastModifiedDate;
        this.roleName = roleName;
        this.description = description;
        this.securityUserRoleList = securityUserRoleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Object createdById) {
        this.createdById = createdById;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public Object getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(Object lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public Object getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Object lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getSecurityUserRoleList() {
        return securityUserRoleList;
    }

    public void setSecurityUserRoleList(Object securityUserRoleList) {
        this.securityUserRoleList = securityUserRoleList;
    }

}
