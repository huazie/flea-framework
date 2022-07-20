package com.huazie.fleaframework.tools.common;

/**
 * <p> 工具包常量 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ToolsConstants {

    interface CodeConstants {
        String AUTHOR = "AUTHOR";
        String VERSION = "VERSION";
        String IS_SELECTED_LOMBOK = "IS_SELECTED_LOMBOK";
        String ROOT_PACKAGE = "ROOT_PACKAGE";
        String CODE_PACKAGE = "CODE_PACKAGE";
        String FLEA_PERSISTENCE_UNIT_NAME = "FLEA_PERSISTENCE_UNIT_NAME";
        String FLEA_PERSISTENCE_UNIT_ALIAS_NAME = "FLEA_PERSISTENCE_UNIT_ALIAS_NAME";
        String FLEA_PERSISTENCE_UNIT_ALIAS_NAME_1 = "FLEA_PERSISTENCE_UNIT_ALIAS_NAME_1";
        String FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE = "FLEA_PERSISTENCE_UNIT_DAO_CLASS_PACKAGE";
        String FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME = "FLEA_PERSISTENCE_UNIT_DAO_CLASS_NAME";
        String TABLE_NAME = "TABLE_NAME";
        String TABLE_NAME_1 = "TABLE_NAME_1";
        String TABLE_DESC = "TABLE_DESC";

        String ENTITY_CLASS_NAME = "ENTITY_CLASS_NAME";
        String ENTITY_CLASS_NAME_1 = "ENTITY_CLASS_NAME_1";
        String CODE_FILE_PATH = "CODE_FILE_PATH";

        String COLUMN_NAME = "COLUMN_NAME";
        String COLUMN_DESC = "COLUMN_DESC";
        String VAR_TYPE = "VAR_TYPE";
        String VARIABLE = "VARIABLE";
        String VARIABLE_1 = "VARIABLE_1";

        String VARIABLE_CODE = "VARIABLE_CODE";
        String METHOD_CODE = "METHOD_CODE";

        String DB_SYSTEM_NAME = "DB_SYSTEM_NAME";
        String DB_NAME = "DB_NAME";

        String NONE = "NONE";
        String ID_GENERATOR_STRATEGY = "ID_GENERATOR_STRATEGY";
        String ID_GENERATOR_TABLE = "ID_GENERATOR_TABLE";
        String PK_COLUMN_NAME = "PK_COLUMN_NAME";
        String VALUE_COLUMN_NAME = "VALUE_COLUMN_NAME";
    }

    interface CodeConfigConstants {

        String CODE_CONFIG_FILE_NAME = "code.properties";

        String CODE_CONFIG_FILE_SYSTEM_KEY = "flea.tools.code.filename";

        String DB_NAME = "dbName";

        String TABLE_NAME = "tableName";

        String TABLE_NAME_DESC = "tableNameDesc";

        String ID_GENERATOR_TABLE = "idGeneratorTable";

        String PK_COLUMN_NAME = "pkColumnName";

        String VALUE_COLUMN_NAME = "valueColumnName";

        String AUTHOR = "author";

        String VERSION = "version";

        String ROOT_PATH = "rootPath";

        String CODE_PACKAGE = "codePackage";

        String PU_DAO_CODE_PACKAGE = "puDaoCodePackage";

        String PU_DAO_CLASS_NAME = "puDaoClassName";

        String PU_NAME = "puName";

        String PU_ALIAS_NAME = "puAliasName";
    }

}
