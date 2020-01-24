# jStrainer

A simple filter chaining mechanism for java objects

A common problem when receiving data from forms, legacy databases or third party services, is the necessity of to filter, sanitize and format this data before it can be processed.
This kind of work turns out to be repetitive, leaving the code complex with many unnecessary lines.
Having this situation present in the dayly of many programmers, this project aims to solve these problems.

### Demo

Demo repository: https://github.com/jstrainer/jstrainer-demo

### Requirements

Java 1.8 or later

### Maven

```xml
<dependencies>
    <dependency>
        <groupId>com.github.jstrainer</groupId>
        <artifactId>jstrainer</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## General Usage

1. Defining the object

```java
package demo;

import com.github.jstrainer.Filtered;
import com.github.jstrainer.filter.Alphanum;
import com.github.jstrainer.filter.Numeric;
import com.github.jstrainer.filter.StripTags;
import com.github.jstrainer.filter.ToUpperCase;
import com.github.jstrainer.filter.Trim;

public class Person {

    @Trim
    @StripTags
    private String name;

    @Filtered
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", address=" + address + "]";
    }

}

class Address {

    @Numeric
    private String postcode;

    @Alphanum(allowSpace = true)
    private String streetName;

    private String buildingNumber;

    private String city;

    @Trim
    private String stateOrRegion;

    @ToUpperCase
    private String country;

    // getters and setters...

    @Override
    public String toString() {
        return "Address [postcode=" + postcode + ", streetName=" + streetName + ", buildingNumber=" + buildingNumber
                + ", city=" + city + ", stateOrRegion=" + stateOrRegion + ", country=" + country + "]";
    }

}
```

Annotations used in the example:

* @Trim - Remove whitespace from both ends of a string
* @Filtered - Apply filters to child object

2. Filter the object

```java
Address address = new Address();
address.setStreetName("*C!o#untry &Street/");
address.setCity("Poughkeepsie");
address.setCountry("usa");
address.setStateOrRegion(" NY ");
address.setPostcode("12603A");
address.setBuildingNumber("539");
    
Person person = new Person();
person.setName("<b>Leonardo</b>           ");
person.setAddress(address);

Strainer strainer = new Strainer();
strainer.filter(person);

System.out.println(person);
    
    /* printed
    Person [
        name=Leonardo, 
        address=Address [
            postcode=12603, 
            streetName=Country Street, 
            buildingNumber=539, 
            city=Poughkeepsie, 
            stateOrRegion=NY, 
            country=USA
        ]
    ] */
```

## Available Filters

| Filter         | Description                                                              | Parameters                           |
|----------------|--------------------------------------------------------------------------|--------------------------------------|
| @Alpha         | Returns only letters                                                     | allowSpace, allowAccents             |
| @Alphanum      | Returns only letters and numbers                                         | allowSpace, allowNewline             |
| @Blacklist     | Returns null if the value is present in the list                         | list                                 |
| @LeftPad       | Appends zeros (or other character) to the left                           | length, padChar                      |
| @Numeric       | Returns only numbers                                                     |                                      |
| @Prefix        | Adds a prefix to a String                                                | value                                |
| @Replace       | Replaces the first or all occurrences of the search with the replacement | search, replacement, all, ignoreCase |
| @RightPad      | Appends zeros (or other character) to the right                          | length, padChar                      |
| @Round         | Rounds a Double                                                          | scale                                |
| @RoundDown     | Rounds a Double down                                                     | scale                                |
| @RoundUp       | Rounds a Double up                                                       | scale                                |
| @StripNewlines | Removes all new line characters                                          |                                      |
| @StripTags     | Removes all tags                                                         |                                      |
| @Suffix        | Adds a suffix to a String                                                | value                                |
| @ToLowerCase   | Converts a String to lower case                                          |                                      |
| @ToUpperCase   | Converts a String to upper case                                          |                                      |
| @Trim          | Removes leading and trailing whitespace (or other character)             | stripChar                            |
| @Whitelist     | Returns null if the value is not present in the list                     | list                                 |

## Creating a custom Filter

1. Add a new @interface to define the filter annotation

```java
package demo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.jstrainer.FilteredBy;

@Documented
@FilteredBy(ReverseFilter.class) // the class that will filter the data
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Reverse {

}
```

2. Implement the filter

```java
package demo;

import com.github.jstrainer.filter.Filter;

public class ReverseFilter implements Filter<Reverse, String> {

    @Override
    public String filter(String value, Reverse annotation) {
        if (value == null) {
            return value;
        }

        return new StringBuffer(value).reverse().toString();
    }

}
```

3. Using the new filter annotation

```java
@Reverse
private String reversed;
```