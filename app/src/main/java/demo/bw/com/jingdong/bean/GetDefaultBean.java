package demo.bw.com.jingdong.bean;

/**
 * Created by lenovo on 2017/12/18.
 */

public class GetDefaultBean extends  BaseBean{

    /**
     * data : {"addr":"北京市昌平区金域国际1-1-1","addrid":719,"mobile":18612991023,"name":"kson","status":1,"uid":100}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr : 北京市昌平区金域国际1-1-1
         * addrid : 719
         * mobile : 18612991023
         * name : kson
         * status : 1
         * uid : 100
         */

        private String addr;
        private int addrid;
        private long mobile;
        private String name;
        private int status;
        private int uid;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getAddrid() {
            return addrid;
        }

        public void setAddrid(int addrid) {
            this.addrid = addrid;
        }

        public long getMobile() {
            return mobile;
        }

        public void setMobile(long mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
