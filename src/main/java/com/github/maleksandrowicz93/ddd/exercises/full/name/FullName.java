package com.github.maleksandrowicz93.ddd.exercises.full.name;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

@Getter(AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@FieldDefaults(makeFinal = true)
class FullName {
    String firstName;
    String middleName;
    String lastName;
    String maidenName;

    public FullName(
            String firstName,
            String middleName,
            String lastName,
            String maidenName
    ) {
        if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
            throw new InvalidNameException();
        }
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.maidenName = maidenName;
    }
}
