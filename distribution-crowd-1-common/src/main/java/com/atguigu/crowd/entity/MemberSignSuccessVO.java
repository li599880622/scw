package com.atguigu.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录成功对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignSuccessVO {

    private String username;
    private String email;
    private String token;

    public static class TypePO {
        private Integer id;

        private String name;

        private String remark;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name == null ? null : name.trim();
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark == null ? null : remark.trim();
        }
    }

    public static class TagPO {
        private Integer id;

        private Integer pid;

        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPid() {
            return pid;
        }

        public void setPid(Integer pid) {
            this.pid = pid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name == null ? null : name.trim();
        }
    }

    public static class ReturnPO {
        private Integer id;

        private Integer projectid;

        private Byte type;

        private Integer supportmoney;

        private String content;

        private Integer count;

        private Integer signalpurchase;

        private Integer purchase;

        private Integer freight;

        private Byte invoice;

        private Integer returndate;

        private String describPicPath;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getProjectid() {
            return projectid;
        }

        public void setProjectid(Integer projectid) {
            this.projectid = projectid;
        }

        public Byte getType() {
            return type;
        }

        public void setType(Byte type) {
            this.type = type;
        }

        public Integer getSupportmoney() {
            return supportmoney;
        }

        public void setSupportmoney(Integer supportmoney) {
            this.supportmoney = supportmoney;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content == null ? null : content.trim();
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getSignalpurchase() {
            return signalpurchase;
        }

        public void setSignalpurchase(Integer signalpurchase) {
            this.signalpurchase = signalpurchase;
        }

        public Integer getPurchase() {
            return purchase;
        }

        public void setPurchase(Integer purchase) {
            this.purchase = purchase;
        }

        public Integer getFreight() {
            return freight;
        }

        public void setFreight(Integer freight) {
            this.freight = freight;
        }

        public Byte getInvoice() {
            return invoice;
        }

        public void setInvoice(Byte invoice) {
            this.invoice = invoice;
        }

        public Integer getReturndate() {
            return returndate;
        }

        public void setReturndate(Integer returndate) {
            this.returndate = returndate;
        }

        public String getDescribPicPath() {
            return describPicPath;
        }

        public void setDescribPicPath(String describPicPath) {
            this.describPicPath = describPicPath == null ? null : describPicPath.trim();
        }
    }

    public static class ProjectPO {
        private Integer id;

        private String projectName;

        private String projectDescription;

        private Long money;

        private Integer day;

        private Byte status;

        private String deploydate;

        private Long supportmoney;

        private Integer supporter;

        private Integer completion;

        private Integer memberid;

        private String createdate;

        private Integer follower;

        private String headerPicturePath;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName == null ? null : projectName.trim();
        }

        public String getProjectDescription() {
            return projectDescription;
        }

        public void setProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription == null ? null : projectDescription.trim();
        }

        public Long getMoney() {
            return money;
        }

        public void setMoney(Long money) {
            this.money = money;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }

        public Byte getStatus() {
            return status;
        }

        public void setStatus(Byte status) {
            this.status = status;
        }

        public String getDeploydate() {
            return deploydate;
        }

        public void setDeploydate(String deploydate) {
            this.deploydate = deploydate == null ? null : deploydate.trim();
        }

        public Long getSupportmoney() {
            return supportmoney;
        }

        public void setSupportmoney(Long supportmoney) {
            this.supportmoney = supportmoney;
        }

        public Integer getSupporter() {
            return supporter;
        }

        public void setSupporter(Integer supporter) {
            this.supporter = supporter;
        }

        public Integer getCompletion() {
            return completion;
        }

        public void setCompletion(Integer completion) {
            this.completion = completion;
        }

        public Integer getMemberid() {
            return memberid;
        }

        public void setMemberid(Integer memberid) {
            this.memberid = memberid;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate == null ? null : createdate.trim();
        }

        public Integer getFollower() {
            return follower;
        }

        public void setFollower(Integer follower) {
            this.follower = follower;
        }

        public String getHeaderPicturePath() {
            return headerPicturePath;
        }

        public void setHeaderPicturePath(String headerPicturePath) {
            this.headerPicturePath = headerPicturePath == null ? null : headerPicturePath.trim();
        }
    }

    public static class ProjectItemPicPO {
        private Integer id;

        private Integer projectid;

        private String itemPicPath;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getProjectid() {
            return projectid;
        }

        public void setProjectid(Integer projectid) {
            this.projectid = projectid;
        }

        public String getItemPicPath() {
            return itemPicPath;
        }

        public void setItemPicPath(String itemPicPath) {
            this.itemPicPath = itemPicPath == null ? null : itemPicPath.trim();
        }
    }

    public static class MemberLaunchInfoPO {
        private Integer id;

        private Integer memberid;

        private String descriptionSimple;

        private String descriptionDetail;

        private String phoneNum;

        private String serviceNum;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getMemberid() {
            return memberid;
        }

        public void setMemberid(Integer memberid) {
            this.memberid = memberid;
        }

        public String getDescriptionSimple() {
            return descriptionSimple;
        }

        public void setDescriptionSimple(String descriptionSimple) {
            this.descriptionSimple = descriptionSimple == null ? null : descriptionSimple.trim();
        }

        public String getDescriptionDetail() {
            return descriptionDetail;
        }

        public void setDescriptionDetail(String descriptionDetail) {
            this.descriptionDetail = descriptionDetail == null ? null : descriptionDetail.trim();
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum == null ? null : phoneNum.trim();
        }

        public String getServiceNum() {
            return serviceNum;
        }

        public void setServiceNum(String serviceNum) {
            this.serviceNum = serviceNum == null ? null : serviceNum.trim();
        }
    }

    public static class MemberConfirmInfoPO {
        private Integer id;

        private Integer memberid;

        private String paynum;

        private String cardnum;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getMemberid() {
            return memberid;
        }

        public void setMemberid(Integer memberid) {
            this.memberid = memberid;
        }

        public String getPaynum() {
            return paynum;
        }

        public void setPaynum(String paynum) {
            this.paynum = paynum == null ? null : paynum.trim();
        }

        public String getCardnum() {
            return cardnum;
        }

        public void setCardnum(String cardnum) {
            this.cardnum = cardnum == null ? null : cardnum.trim();
        }
    }
}
