package demo.bw.com.jingdong.bean;

import java.util.List;

/**
 * Created by lenovo on 2017/12/18.
 */

public class NewlistBean {
    private long ver;
    private int server_time;
    private List<GoodsListBean> goods_list;

    public long getVer() {
        return ver;
    }

    public void setVer(long ver) {
        this.ver = ver;
    }

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class GoodsListBean {
        /**
         * normal_price : 10000
         * cnt : 3136
         * thumb_url : http://omsproductionimg.yangkeduo.com/images/2017-12-06/50d6255de1fb2f3b4ac518ee9c954395.jpeg
         * event_type : 0
         * country :
         * short_name : 【多款爆款任选】睡衣女冬加厚三层夹棉纯棉加肥加大码学生韩版卡通可爱少女秋冬季保暖月子服女士家居服套装胖MM棉袄中老年妈妈
         * group : {"customer_num":2,"price":9900}
         * allowed_region : 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,30,31,32
         * time : 1513582221
         * image_url : http://omsproductionimg.yangkeduo.com/images/2017-12-14/60dd2ac0373100fc2571514270b7e48c.jpeg
         * market_price : 29800
         * goods_name : 【多款爆款任选】睡衣女冬加厚三层夹棉纯棉加肥加大码学生韩版卡通可爱少女秋冬季保暖月子服女士家居服套装胖MM棉袄中老年妈妈
         * hd_thumb_url : http://omsproductionimg.yangkeduo.com/images/2017-12-06/36f39a717038631142457d3328065566.jpeg
         * quantity : 3486
         * is_onsale : 1
         * goods_id : 173580312
         * is_app : 0
         * mall_id : 774619
         * region_limit : 1
         */

        private int normal_price;
        private int cnt;
        private String thumb_url;
        private int event_type;
        private String country;
        private String short_name;
        private GroupBean group;
        private String allowed_region;
        private int time;
        private String image_url;
        private int market_price;
        private String goods_name;
        private String hd_thumb_url;
        private int quantity;
        private int is_onsale;
        private int goods_id;
        private int is_app;
        private int mall_id;
        private int region_limit;

        public int getNormal_price() {
            return normal_price;
        }

        public void setNormal_price(int normal_price) {
            this.normal_price = normal_price;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public String getThumb_url() {
            return thumb_url;
        }

        public void setThumb_url(String thumb_url) {
            this.thumb_url = thumb_url;
        }

        public int getEvent_type() {
            return event_type;
        }

        public void setEvent_type(int event_type) {
            this.event_type = event_type;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public GroupBean getGroup() {
            return group;
        }

        public void setGroup(GroupBean group) {
            this.group = group;
        }

        public String getAllowed_region() {
            return allowed_region;
        }

        public void setAllowed_region(String allowed_region) {
            this.allowed_region = allowed_region;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public int getMarket_price() {
            return market_price;
        }

        public void setMarket_price(int market_price) {
            this.market_price = market_price;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getHd_thumb_url() {
            return hd_thumb_url;
        }

        public void setHd_thumb_url(String hd_thumb_url) {
            this.hd_thumb_url = hd_thumb_url;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getIs_onsale() {
            return is_onsale;
        }

        public void setIs_onsale(int is_onsale) {
            this.is_onsale = is_onsale;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public int getIs_app() {
            return is_app;
        }

        public void setIs_app(int is_app) {
            this.is_app = is_app;
        }

        public int getMall_id() {
            return mall_id;
        }

        public void setMall_id(int mall_id) {
            this.mall_id = mall_id;
        }

        public int getRegion_limit() {
            return region_limit;
        }

        public void setRegion_limit(int region_limit) {
            this.region_limit = region_limit;
        }

        public static class GroupBean {
            /**
             * customer_num : 2
             * price : 9900
             */

            private int customer_num;
            private int price;

            public int getCustomer_num() {
                return customer_num;
            }

            public void setCustomer_num(int customer_num) {
                this.customer_num = customer_num;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }
}
