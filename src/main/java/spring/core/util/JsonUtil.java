package spring.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;


public final class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static String parseFile(MultipartFile multipart) {
        String jsonString = null;
        try (ByteArrayInputStream stream = new ByteArrayInputStream(multipart.getBytes())) {
            jsonString = IOUtils.toString(stream, "UTF-8");
        } catch (IOException e) {
            log.error("json read error", e);
        }
        return jsonString;
    }

    public static <T> T getEntities(String jsonStr, Class<T> className) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, className);
    }
}
