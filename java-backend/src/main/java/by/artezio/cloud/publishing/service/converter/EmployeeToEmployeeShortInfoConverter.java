package by.artezio.cloud.publishing.service.converter;

import by.artezio.cloud.publishing.domain.Employee;
import by.artezio.cloud.publishing.dto.EmployeeShortInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Класс для конвертации {@link Employee} в {@link EmployeeShortInfo}.
 * @author Denis Shubin
 */
@Component
public class EmployeeToEmployeeShortInfoConverter implements Converter<Employee, EmployeeShortInfo> {

    @Override
    public EmployeeShortInfo convert(final Employee employee) {
        EmployeeShortInfo shortInfo = new EmployeeShortInfo();
        shortInfo.setId(employee.getId());
        shortInfo.setShortFullName(getShortFullName(employee));
        return shortInfo;
    }

    /**
     * Получить строку вида Фамилия И.[О.].
     * @param e сотрудник
     * @return Фамилия И.[О.]
     */
    private String getShortFullName(final Employee e) {
        StringBuilder bldr = new StringBuilder();
        bldr
            .append(e.getLastName())
            .append(" ")
            .append(e.getFirstName().charAt(0))
            .append(". ");
        String middle = e.getMiddleName();
        if (middle != null && !middle.isEmpty()) {
            bldr
                .append(middle.charAt(0))
                .append(".");
        }
        return bldr.toString();
    }
}
