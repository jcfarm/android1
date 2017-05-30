package scujcc.com.farm_demo.bean;

import java.util.List;

/**
 * Created by hello-brothers on 2017/5/29.
 */

public class LogBean {

    private List<ItemLogBean> log;

    public List<ItemLogBean> getLog() {
        return log;
    }

    public void setLog(List<ItemLogBean> log) {
        this.log = log;
    }

    public static class ItemLogBean {
        /**
         * id : 1
         * target : 1
         * action : {"type":"light","commond":1}
         * time : 1493800169
         * sender : xiaoming
         */

        private int id;
        private String target;
        private ActionBean action;
        private int time;
        private String sender;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public ActionBean getAction() {
            return action;
        }

        public void setAction(ActionBean action) {
            this.action = action;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public static class ActionBean {
            /**
             * type : light
             * commond : 1
             */

            private String type;
            private int commond;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getCommond() {
                return commond;
            }

            public void setCommond(int commond) {
                this.commond = commond;
            }
        }
    }
}
