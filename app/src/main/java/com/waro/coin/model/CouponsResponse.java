package com.waro.coin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CouponsResponse {


    @SerializedName("status")
    private String status;
    @SerializedName("coupons")
    private List<CouponsBean> coupons;
    @SerializedName("coupons_usage")
    private List<CouponsUsageBean> couponsUsage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CouponsBean> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponsBean> coupons) {
        this.coupons = coupons;
    }

    public List<CouponsUsageBean> getCouponsUsage() {
        return couponsUsage;
    }

    public void setCouponsUsage(List<CouponsUsageBean> couponsUsage) {
        this.couponsUsage = couponsUsage;
    }

    public static class CouponsBean {
        @SerializedName("id")
        private int id;
        @SerializedName("coupon_code")
        private String couponCode;
        @SerializedName("type")
        private String type;
        @SerializedName("value")
        private String value;
        @SerializedName("status")
        private String status;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCouponCode() {
            return couponCode;
        }

        public void setCouponCode(String couponCode) {
            this.couponCode = couponCode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    public static class CouponsUsageBean {
        @SerializedName("coupon_id")
        private String couponId;
        @SerializedName("coupons_usage")
        private String couponsUsage;

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCouponsUsage() {
            return couponsUsage;
        }

        public void setCouponsUsage(String couponsUsage) {
            this.couponsUsage = couponsUsage;
        }
    }
}
