package com.waro.coin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderHistoryResponse implements Parcelable {

    @SerializedName("status")
    private String status;
    @SerializedName("orders")
    private ArrayList<OrdersBean> orders;

    protected OrderHistoryResponse(Parcel in) {
        status = in.readString();
    }

    public static final Creator<OrderHistoryResponse> CREATOR = new Creator<OrderHistoryResponse>() {
        @Override
        public OrderHistoryResponse createFromParcel(Parcel in) {
            return new OrderHistoryResponse(in);
        }

        @Override
        public OrderHistoryResponse[] newArray(int size) {
            return new OrderHistoryResponse[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<OrdersBean> orders) {
        this.orders = orders;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
    }

    public static class OrdersBean implements Parcelable{
        @SerializedName("id")
        private int id;
        @SerializedName("customer_id")
        private String customerId;
        @SerializedName("delivery_charges")
        private String deliveryCharges;
        @SerializedName("discount_amt")
        private String discountAmt;
        @SerializedName("total_amt")
        private String totalAmt;
        @SerializedName("address_id")
        private String addressId;
        @SerializedName("shop_id")
        private String shopId;
        @SerializedName("coupon_id")
        private String couponId;
        @SerializedName("customer_comments")
        private String customerComments;
        @SerializedName("status")
        private String status;
        @SerializedName("comments")
        private String comments;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("date_time")
        private String dateTime;
        @SerializedName("address")
        private AddressBean address;
        @SerializedName("shop")
        private ShopBean shop;
        @SerializedName("coupon")
        private CouponBean coupon;
        @SerializedName("order_item")
        private ArrayList<OrderItemBean> orderItem;

        protected OrdersBean(Parcel in) {
            id = in.readInt();
            customerId = in.readString();
            deliveryCharges = in.readString();
            totalAmt = in.readString();
            addressId = in.readString();
            shopId = in.readString();
            couponId = in.readString();
            status = in.readString();
            createdAt = in.readString();
            updatedAt = in.readString();
            dateTime = in.readString();
            address = in.readParcelable(AddressBean.class.getClassLoader());
        }

        public static final Creator<OrdersBean> CREATOR = new Creator<OrdersBean>() {
            @Override
            public OrdersBean createFromParcel(Parcel in) {
                return new OrdersBean(in);
            }

            @Override
            public OrdersBean[] newArray(int size) {
                return new OrdersBean[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getDeliveryCharges() {
            return deliveryCharges;
        }

        public void setDeliveryCharges(String deliveryCharges) {
            this.deliveryCharges = deliveryCharges;
        }

        public String getDiscountAmt() {
            return discountAmt;
        }

        public void setDiscountAmt(String discountAmt) {
            this.discountAmt = discountAmt;
        }

        public String getTotalAmt() {
            return totalAmt;
        }

        public void setTotalAmt(String totalAmt) {
            this.totalAmt = totalAmt;
        }

        public String getAddressId() {
            return addressId;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCustomerComments() {
            return customerComments;
        }

        public void setCustomerComments(String customerComments) {
            this.customerComments = customerComments;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
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

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
        }
        public ArrayList<OrderItemBean> getOrderItem() {
            return orderItem;
        }

        public void setOrderItem(ArrayList<OrderItemBean> orderItem) {
            this.orderItem = orderItem;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(customerId);
            dest.writeString(deliveryCharges);
            dest.writeString(totalAmt);
            dest.writeString(addressId);
            dest.writeString(shopId);
            dest.writeString(couponId);
            dest.writeString(status);
            dest.writeString(createdAt);
            dest.writeString(updatedAt);
            dest.writeString(dateTime);
            dest.writeParcelable(address, flags);
        }

        public static class AddressBean implements Parcelable {
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;
            @SerializedName("phone")
            private String phone;
            @SerializedName("customer_id")
            private String customerId;
            @SerializedName("addr1")
            private String addr1;
            @SerializedName("addr2")
            private String addr2;
            @SerializedName("pincode")
            private String pincode;
            @SerializedName("landmark")
            private String landmark;
            @SerializedName("updated_at")
            private String updatedAt;
            @SerializedName("created_at")
            private String createdAt;

            protected AddressBean(Parcel in) {
                id = in.readInt();
                name = in.readString();
                phone = in.readString();
                customerId = in.readString();
                addr1 = in.readString();
                addr2 = in.readString();
                pincode = in.readString();
                landmark = in.readString();
                updatedAt = in.readString();
                createdAt = in.readString();
            }

            public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
                @Override
                public AddressBean createFromParcel(Parcel in) {
                    return new AddressBean(in);
                }

                @Override
                public AddressBean[] newArray(int size) {
                    return new AddressBean[size];
                }
            };

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getAddr1() {
                return addr1;
            }

            public void setAddr1(String addr1) {
                this.addr1 = addr1;
            }

            public String getAddr2() {
                return addr2;
            }

            public void setAddr2(String addr2) {
                this.addr2 = addr2;
            }

            public String getPincode() {
                return pincode;
            }

            public void setPincode(String pincode) {
                this.pincode = pincode;
            }

            public String getLandmark() {
                return landmark;
            }

            public void setLandmark(String landmark) {
                this.landmark = landmark;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(name);
                dest.writeString(phone);
                dest.writeString(customerId);
                dest.writeString(addr1);
                dest.writeString(addr2);
                dest.writeString(pincode);
                dest.writeString(landmark);
                dest.writeString(updatedAt);
                dest.writeString(createdAt);
            }
        }

        public static class ShopBean implements Parcelable{
            @SerializedName("id")
            private int id;
            @SerializedName("shopname")
            private String shopname;
            @SerializedName("slug")
            private String slug;
            @SerializedName("description")
            private String description;
            @SerializedName("state_id")
            private String stateId;
            @SerializedName("city_id")
            private String cityId;
            @SerializedName("category_id")
            private String categoryId;
            @SerializedName("address")
            private String address;
            @SerializedName("phone")
            private String phone;
            @SerializedName("website")
            private String website;
            @SerializedName("opentime")
            private String opentime;
            @SerializedName("closetime")
            private String closetime;
            @SerializedName("delivery_charges")
            private String deliveryCharges;
            @SerializedName("image")
            private String image;
            @SerializedName("status")
            private String status;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("updated_at")
            private String updatedAt;

            protected ShopBean(Parcel in) {
                id = in.readInt();
                shopname = in.readString();
                slug = in.readString();
                description = in.readString();
                stateId = in.readString();
                cityId = in.readString();
                categoryId = in.readString();
                address = in.readString();
                phone = in.readString();
                website = in.readString();
                opentime = in.readString();
                closetime = in.readString();
                deliveryCharges = in.readString();
                image = in.readString();
                status = in.readString();
                createdAt = in.readString();
                updatedAt = in.readString();
            }

            public static final Creator<ShopBean> CREATOR = new Creator<ShopBean>() {
                @Override
                public ShopBean createFromParcel(Parcel in) {
                    return new ShopBean(in);
                }

                @Override
                public ShopBean[] newArray(int size) {
                    return new ShopBean[size];
                }
            };

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getStateId() {
                return stateId;
            }

            public void setStateId(String stateId) {
                this.stateId = stateId;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getOpentime() {
                return opentime;
            }

            public void setOpentime(String opentime) {
                this.opentime = opentime;
            }

            public String getClosetime() {
                return closetime;
            }

            public void setClosetime(String closetime) {
                this.closetime = closetime;
            }

            public String getDeliveryCharges() {
                return deliveryCharges;
            }

            public void setDeliveryCharges(String deliveryCharges) {
                this.deliveryCharges = deliveryCharges;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(shopname);
                dest.writeString(slug);
                dest.writeString(description);
                dest.writeString(stateId);
                dest.writeString(cityId);
                dest.writeString(categoryId);
                dest.writeString(address);
                dest.writeString(phone);
                dest.writeString(website);
                dest.writeString(opentime);
                dest.writeString(closetime);
                dest.writeString(deliveryCharges);
                dest.writeString(image);
                dest.writeString(status);
                dest.writeString(createdAt);
                dest.writeString(updatedAt);
            }
        }

        public static class CouponBean implements Parcelable {
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

            protected CouponBean(Parcel in) {
                id = in.readInt();
                couponCode = in.readString();
                type = in.readString();
                value = in.readString();
                status = in.readString();
                createdAt = in.readString();
                updatedAt = in.readString();
            }

            public static final Creator<CouponBean> CREATOR = new Creator<CouponBean>() {
                @Override
                public CouponBean createFromParcel(Parcel in) {
                    return new CouponBean(in);
                }

                @Override
                public CouponBean[] newArray(int size) {
                    return new CouponBean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(couponCode);
                dest.writeString(type);
                dest.writeString(value);
                dest.writeString(status);
                dest.writeString(createdAt);
                dest.writeString(updatedAt);
            }
        }

        public static class OrderItemBean implements Parcelable {
            @SerializedName("id")
            private int id;
            @SerializedName("order_id")
            private String orderId;
            @SerializedName("item_id")
            private String itemId;
            @SerializedName("shop_id")
            private String shopId;
            @SerializedName("qty")
            private String qty;
            @SerializedName("price")
            private String price;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("updated_at")
            private String updatedAt;
            @SerializedName("item")
            private ItemBean item;

            protected OrderItemBean(Parcel in) {
                id = in.readInt();
                orderId = in.readString();
                itemId = in.readString();
                shopId = in.readString();
                qty = in.readString();
                price = in.readString();
                createdAt = in.readString();
                updatedAt = in.readString();
            }

            public static final Creator<OrderItemBean> CREATOR = new Creator<OrderItemBean>() {
                @Override
                public OrderItemBean createFromParcel(Parcel in) {
                    return new OrderItemBean(in);
                }

                @Override
                public OrderItemBean[] newArray(int size) {
                    return new OrderItemBean[size];
                }
            };

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getItemId() {
                return itemId;
            }

            public void setItemId(String itemId) {
                this.itemId = itemId;
            }

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public String getQty() {
                return qty;
            }

            public void setQty(String qty) {
                this.qty = qty;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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

            public ItemBean getItem() {
                return item;
            }

            public void setItem(ItemBean item) {
                this.item = item;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeString(orderId);
                dest.writeString(itemId);
                dest.writeString(shopId);
                dest.writeString(qty);
                dest.writeString(price);
                dest.writeString(createdAt);
                dest.writeString(updatedAt);
            }

            public static class ItemBean implements Parcelable {
                @SerializedName("id")
                private int id;
                @SerializedName("itemname")
                private String itemname;
                @SerializedName("slug")
                private String slug;
                @SerializedName("qty")
                private String qty;
                @SerializedName("shop_id")
                private String shopId;
                @SerializedName("category_id")
                private String categoryId;
                @SerializedName("subcategory_id")
                private String subcategoryId;
                @SerializedName("price")
                private String price;
                @SerializedName("description")
                private String description;
                @SerializedName("choices")
                private String choices;
                @SerializedName("image")
                private String image;
                @SerializedName("status")
                private String status;
                @SerializedName("created_at")
                private String createdAt;
                @SerializedName("updated_at")
                private String updatedAt;

                protected ItemBean(Parcel in) {
                    id = in.readInt();
                    itemname = in.readString();
                    slug = in.readString();
                    qty = in.readString();
                    shopId = in.readString();
                    categoryId = in.readString();
                    subcategoryId = in.readString();
                    price = in.readString();
                    description = in.readString();
                    choices = in.readString();
                    image = in.readString();
                    status = in.readString();
                    createdAt = in.readString();
                    updatedAt = in.readString();
                }

                public static final Creator<ItemBean> CREATOR = new Creator<ItemBean>() {
                    @Override
                    public ItemBean createFromParcel(Parcel in) {
                        return new ItemBean(in);
                    }

                    @Override
                    public ItemBean[] newArray(int size) {
                        return new ItemBean[size];
                    }
                };

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getItemname() {
                    return itemname;
                }

                public void setItemname(String itemname) {
                    this.itemname = itemname;
                }

                public String getSlug() {
                    return slug;
                }

                public void setSlug(String slug) {
                    this.slug = slug;
                }

                public String getQty() {
                    return qty;
                }

                public void setQty(String qty) {
                    this.qty = qty;
                }

                public String getShopId() {
                    return shopId;
                }

                public void setShopId(String shopId) {
                    this.shopId = shopId;
                }

                public String getCategoryId() {
                    return categoryId;
                }

                public void setCategoryId(String categoryId) {
                    this.categoryId = categoryId;
                }

                public String getSubcategoryId() {
                    return subcategoryId;
                }

                public void setSubcategoryId(String subcategoryId) {
                    this.subcategoryId = subcategoryId;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getChoices() {
                    return choices;
                }

                public void setChoices(String choices) {
                    this.choices = choices;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(id);
                    dest.writeString(itemname);
                    dest.writeString(slug);
                    dest.writeString(qty);
                    dest.writeString(shopId);
                    dest.writeString(categoryId);
                    dest.writeString(subcategoryId);
                    dest.writeString(price);
                    dest.writeString(description);
                    dest.writeString(choices);
                    dest.writeString(image);
                    dest.writeString(status);
                    dest.writeString(createdAt);
                    dest.writeString(updatedAt);
                }
            }
        }
    }
}
