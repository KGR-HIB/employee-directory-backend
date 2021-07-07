package com.hiberus.employee.directory.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.springframework.util.CollectionUtils;
import com.hiberus.employee.directory.vo.Employee;

/**
 * FileUtil.
 * 
 * @author acachiguango on 05/07/2021
 * @version 1.0
 */
public final class FileUtil {
    /**
     * Constructor.
     */
    private FileUtil() {
    }

    /**
     * Get photo base64.
     * 
     * @author acachiguango on 05/07/2021
     * @param employeeId employee Id
     * @return file
     */
    public static String getBase64(Integer employeeId) {
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(
                new File("src/main/resources/files/".concat(employeeId.toString().concat(".png"))));
            if (null != fileContent) {
                return Base64.getEncoder().encodeToString(fileContent);
            }
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * Save photo.
     * 
     * @author acachiguango on 05/07/2021
     * @param bytes file
     * @param employeeId employee Id
     */
    public static void saveFile(byte[] bytes, Integer employeeId) {
        try {
            if (null != bytes) {
                Path path = Paths.get("src/main/resources/files/".concat(employeeId.toString()).concat(".png"));
                Files.write(path, bytes);
            }
        } catch (IOException e) {
        }
    }

    /**
     * Add base64 photos.
     * 
     * @author acachiguango on 05/07/2021
     * @param employees list employees
     */
    public static void addBase64Photos(List<Employee> employees) {
        if (!CollectionUtils.isEmpty(employees)) {
            for (Employee employee : employees) {
                employee.setPhoto(getBase64(employee.getId()));
            }
        }
    }

    /**
     * Add base64 photo.
     * 
     * @author acachiguango on 05/07/2021
     * @param Employee employee
     */
    public static void addBase64Photo(Employee employee) {
        if (null != employee) {
            employee.setPhoto(getBase64(employee.getId()));
        }
    }

}
