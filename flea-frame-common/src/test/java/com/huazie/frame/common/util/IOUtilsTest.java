package com.huazie.frame.common.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

public class IOUtilsTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(IOUtilsTest.class);

    @Test
    public void testToString() {
        InputStream inputStream = IOUtils.getInputStreamFromClassPath("flea/flea-config.xml");
        String input = IOUtils.toString(inputStream);
        LOGGER.debug("INPUT STRING : \n{}", input);
    }

    @Test
    public void testToFile() {
        String input = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4NCg0KPGZsZWEtY29uZmlnPg0K\n" +
                "ICAgIDxjb25maWctaXRlbXMga2V5PSJmbGVhLWplcnNleS1jbGllbnQiIGRlc2M9IkZsZWEgSmVy\n" +
                "c2V5IOWuouaIt+err+mFjee9riI+DQogICAgICAgIDxjb25maWctaXRlbSBrZXk9InN5c3RlbV91\n" +
                "c2VyX2lkIiBkZXNjPSLns7vnu5/nlKjmiLfnvJblj7ciPjEwMDA8L2NvbmZpZy1pdGVtPg0KICAg\n" +
                "ICAgICA8Y29uZmlnLWl0ZW0ga2V5PSJzeXN0ZW1fdXNlcl9wd2QiIGRlc2M9Iuezu+e7n+eUqOaI\n" +
                "t+WvhueggSI+RTEyMEVBMjgwQUE1MDY5M0Q1NTY4RDAwNzE0NTY0NjA8L2NvbmZpZy1pdGVtPg0K\n" +
                "ICAgIDwvY29uZmlnLWl0ZW1zPg0KDQogICAgPGNvbmZpZy1pdGVtcyBrZXk9ImZsZWEtaTE4bi1j\n" +
                "b25maWciIGRlc2M9IkZsZWHlm73pmYXljJbnm7jlhbPphY3nva4iPg0KICAgICAgICA8Y29uZmln\n" +
                "LWl0ZW0ga2V5PSJlcnJvciIgZGVzYz0iZXJyb3Llm73pmYXljJbotYTmupDnibnmrorphY3nva7v\n" +
                "vIzmjIflrprot6/lvoTlkozmlofku7bliY3nvIDvvIzpgJflj7fliIbpmpQiPmZsZWEvaTE4bixm\n" +
                "bGVhX2kxOG48L2NvbmZpZy1pdGVtPg0KICAgIDwvY29uZmlnLWl0ZW1zPg0KDQogICAgPGNvbmZp\n" +
                "Zy1pdGVtcyBrZXk9Im15c3FsLWZsZWFjb25maWciIGRlc2M9IkpEQkPmlbDmja7lupPphY3nva7j\n" +
                "gJBrZXk95pWw5o2u5bqT57O757ufLeaVsOaNruW6k+aIluaVsOaNruW6k+eUqOaIt+OAkSI+DQog\n" +
                "ICAgICAgIDxjb25maWctaXRlbSBrZXk9ImRyaXZlciIgZGVzYz0ibXlzcWzmlbDmja7lupPpqbHl\n" +
                "iqjlkI0iPmNvbS5teXNxbC5qZGJjLkRyaXZlcjwvY29uZmlnLWl0ZW0+DQogICAgICAgIDxjb25m\n" +
                "aWctaXRlbSBrZXk9InVybCIgZGVzYz0ibXlzcWzmlbDmja7lupPov57mjqXlnLDlnYAiPmpkYmM6\n" +
                "bXlzcWw6Ly9sb2NhbGhvc3Q6MzMwNi9mbGVhY29uZmlnP3VzZVVuaWNvZGU9dHJ1ZSZhbXA7Y2hh\n" +
                "cmFjdGVyRW5jb2Rpbmc9VVRGLTg8L2NvbmZpZy1pdGVtPg0KICAgICAgICA8Y29uZmlnLWl0ZW0g\n" +
                "a2V5PSJ1c2VyIiBkZXNjPSJteXNxbOaVsOaNruW6k+eZu+W9leeUqOaIt+WQjSI+cm9vdDwvY29u\n" +
                "ZmlnLWl0ZW0+DQogICAgICAgIDxjb25maWctaXRlbSBrZXk9InBhc3N3b3JkIiBkZXNjPSJteXNx\n" +
                "bOaVsOaNruW6k+eZu+W9leWvhueggSI+cm9vdDwvY29uZmlnLWl0ZW0+DQogICAgPC9jb25maWct\n" +
                "aXRlbXM+DQoNCiAgICA8Y29uZmlnLWl0ZW1zIGtleT0ibXlzcWwtZmxlYW1hcmtldCIgZGVzYz0i\n" +
                "SkRCQ+aVsOaNruW6k+mFjee9ruOAkGtleT3mlbDmja7lupPns7vnu58t5pWw5o2u5bqT5oiW5pWw\n" +
                "5o2u5bqT55So5oi344CRIj4NCiAgICAgICAgPGNvbmZpZy1pdGVtIGtleT0iZHJpdmVyIiBkZXNj\n" +
                "PSJteXNxbOaVsOaNruW6k+mpseWKqOWQjSI+Y29tLm15c3FsLmpkYmMuRHJpdmVyPC9jb25maWct\n" +
                "aXRlbT4NCiAgICAgICAgPGNvbmZpZy1pdGVtIGtleT0idXJsIiBkZXNjPSJteXNxbOaVsOaNruW6\n" +
                "k+i/nuaOpeWcsOWdgCI+amRiYzpteXNxbDovL2xvY2FsaG9zdDozMzA2L2ZsZWFtYXJrZXQ/dXNl\n" +
                "VW5pY29kZT10cnVlJmFtcDtjaGFyYWN0ZXJFbmNvZGluZz1VVEYtODwvY29uZmlnLWl0ZW0+DQog\n" +
                "ICAgICAgIDxjb25maWctaXRlbSBrZXk9InVzZXIiIGRlc2M9Im15c3Fs5pWw5o2u5bqT55m75b2V\n" +
                "55So5oi35ZCNIj5yb290PC9jb25maWctaXRlbT4NCiAgICAgICAgPGNvbmZpZy1pdGVtIGtleT0i\n" +
                "cGFzc3dvcmQiIGRlc2M9Im15c3Fs5pWw5o2u5bqT55m75b2V5a+G56CBIj5yb290PC9jb25maWct\n" +
                "aXRlbT4NCiAgICA8L2NvbmZpZy1pdGVtcz4NCg0KICAgIDxjb25maWctaXRlbXMga2V5PSJteXNx\n" +
                "bC1mbGVhbWFuYWdlbWVudCIgZGVzYz0iSkRCQ+aVsOaNruW6k+mFjee9ruOAkGtleT3mlbDmja7l\n" +
                "upPns7vnu58t5pWw5o2u5bqT5oiW5pWw5o2u5bqT55So5oi344CRIj4NCiAgICAgICAgPGNvbmZp\n" +
                "Zy1pdGVtIGtleT0iZHJpdmVyIiBkZXNjPSJteXNxbOaVsOaNruW6k+mpseWKqOWQjSI+Y29tLm15\n" +
                "c3FsLmpkYmMuRHJpdmVyPC9jb25maWctaXRlbT4NCiAgICAgICAgPGNvbmZpZy1pdGVtIGtleT0i\n" +
                "dXJsIiBkZXNjPSJteXNxbOaVsOaNruW6k+i/nuaOpeWcsOWdgCI+amRiYzpteXNxbDovL2xvY2Fs\n" +
                "aG9zdDozMzA2L2ZsZWFtYW5hZ2VtZW50P3VzZVVuaWNvZGU9dHJ1ZSZhbXA7Y2hhcmFjdGVyRW5j\n" +
                "b2Rpbmc9VVRGLTg8L2NvbmZpZy1pdGVtPg0KICAgICAgICA8Y29uZmlnLWl0ZW0ga2V5PSJ1c2Vy\n" +
                "IiBkZXNjPSJteXNxbOaVsOaNruW6k+eZu+W9leeUqOaIt+WQjSI+cm9vdDwvY29uZmlnLWl0ZW0+\n" +
                "DQogICAgICAgIDxjb25maWctaXRlbSBrZXk9InBhc3N3b3JkIiBkZXNjPSJteXNxbOaVsOaNruW6\n" +
                "k+eZu+W9leWvhueggSI+cm9vdDwvY29uZmlnLWl0ZW0+DQogICAgPC9jb25maWctaXRlbXM+DQoN\n" +
                "CjwvZmxlYS1jb25maWc+";
        File file = IOUtils.toFile(input, "E:\\flea-config.xml");
        LOGGER.debug("FILE : {}", file);
    }

    @Test
    public void testOtherFile() {
        File file = new File("E:\\IMG.jpg");
        String input = IOUtils.toString(file);
        IOUtils.toFile(input, "D:\\IMG_1.jpg");
    }
}