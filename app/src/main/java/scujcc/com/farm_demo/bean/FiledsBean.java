package scujcc.com.farm_demo.bean;

import java.util.List;

/**
 * Created by hello-brothers on 2017/5/4.
 */

public class FiledsBean {
    private List<ItemFieldsBean> fields;

    public List<ItemFieldsBean> getFields() {
        return fields;
    }

    public void setFields(List<ItemFieldsBean> fields) {
        this.fields = fields;
    }

    public static class ItemFieldsBean {
        /**
         * id : 1
         * url : resp: //12343412
         * temp : 20
         */

        private int id;
        private String url;
        private int temp;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getTemp() {
            return temp;
        }

        public void setTemp(int temp) {
            this.temp = temp;
        }
    }
}
