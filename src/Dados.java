public class Dados {
        private String key;
        private String value;
        private String module;
            public Dados(String key, String value, String module) {
                this.key = key;
                this.value = value;
                this.module = module;
            }
                public String getKey() {return key;}
                public void setKey(String key) {
            this.key = key;
        }
                public String getValue() {return value;}
                public void setValue(String value) {
            this.value = value;
        }
                public String getModule() {return module;}
                public void setModule(String module) {this.module = module;}
    }


