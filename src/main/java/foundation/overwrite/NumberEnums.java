package foundation.overwrite;

import com.sun.scenario.effect.impl.prism.PrImage;

/**
 * @author zhoufan
 * @create 2018-01-30 14:02
 **/
public enum NumberEnums {
    ONE(1,"1"),TWO(2,"2"),THREE(3,"3");

    private Integer code;
    private String desc;

    NumberEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
