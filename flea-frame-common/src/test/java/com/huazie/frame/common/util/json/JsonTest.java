package com.huazie.frame.common.util.json;

import com.huazie.frame.common.i18n.pojo.FleaI18nData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class JsonTest {

    @Test
    public void testFastJson() {
        FleaI18nData data = new FleaI18nData();
        data.setKey("CACHE0000001");
        data.setValue("你是谁");

        FleaI18nData data1 = new FleaI18nData();
        data1.setKey("CACHE0000002");
        data1.setValue("我是谁");

        List<FleaI18nData> entityList = new ArrayList<FleaI18nData>();
        entityList.add(data);
        entityList.add(data1);

        // 1. 对象转成JSON串
        String jsonString = FastJsonUtils.toJsonString(data);
        String jsonString1 = FastJsonUtils.toJsonString(entityList);

        // 2. 转换成实体对象
        FastJsonUtils.toEntity(jsonString, FleaI18nData.class);

        // 3. 转换成实体对象的List集合
        FastJsonUtils.toEntityList(jsonString1, FleaI18nData.class);

        // 4. 转换成Map对象
        FastJsonUtils.toMap(jsonString);

        // 5. 转换成Map对象的List集合
        FastJsonUtils.toMapList(jsonString1);
    }

    @Test
    public void testGsonJson() {
        FleaI18nData data = new FleaI18nData();
        data.setKey("CACHE0000003");
        data.setValue("你是来自何方");

        FleaI18nData data1 = new FleaI18nData();
        data1.setKey("CACHE0000004");
        data1.setValue("我要去往何方");

        List<FleaI18nData> entityList = new ArrayList<FleaI18nData>();
        entityList.add(data);
        entityList.add(data1);

        // 1. 对象转成JSON串
        String jsonString = GsonUtils.toJsonString(data);
        String jsonString1 = GsonUtils.toJsonString(entityList);

        // 2. 转换成实体对象
        GsonUtils.toEntity(jsonString, FleaI18nData.class);

        // 3. 转换成实体对象的List集合
        GsonUtils.toEntityList(jsonString1, FleaI18nData.class);

        // 4. 转换成Map对象
        GsonUtils.toMap(jsonString);

        // 5. 转换成Map对象的List集合
        GsonUtils.toMapList(jsonString1);
    }

    @Test
    public void testJson() {

        String json = "{\"liststring\":[\"beijing\",\"shanghai\",\"hangzhou\"]}";

        // 转换成String对象的List集合
        JsonUtils.toStringList(json, "liststring");

        // 转换成Map对象的List集合
        String json1 = "{\"listmap\":[{\"address\":\"beijing\", \"name\":\"jack\", \"id\":1001}, {\"address\": \"shanghai\", \"name\":\"rose\", \"id\":1002}]}";
        JsonUtils.toMapList(json1, "listmap");
    }
}

