package com.wd.logindemo2.bean;

import java.math.BigDecimal;

/**
 * @Author：Administrator
 * @E-mail： victory52@163.com
 * @Date：2019/4/26 10:41
 * @Description：描述信息
 */
public class Goods {
    /**
     * commodityId : 142
     * commodityName : 秋季爆款高帮帆布鞋 街头防滑双色阴阳鞋 男士高帮帆布鞋
     * masterPic : http://172.17.8.100/images/small/commodity/nx/nfbx/1/1.jpg
     * price : 109
     * saleNum : 0
     */

    private int commodityId;
    private String commodityName;
    private String masterPic;
    private int price;
    private BigDecimal saleNum;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getMasterPic() {
        return masterPic;
    }

    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BigDecimal getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(BigDecimal saleNum) {
        this.saleNum = saleNum;
    }

}
