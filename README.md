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
        <version>1.0.0</version>
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
* [Other filters](#available-filters)

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

| Filter | Parameters | Property Type | Description |
|--------|------------|---------------|-------------|
| @Alpha | allowSpace, allowAccents | String | Returns only letters |
| @Alphanum | allowSpace, allowNewline | String | Returns only letters and numbers |
| @Blacklist | list | String | Returns null if the value is present in the list |
| @DefaultValue | value | String | Returns a default value if value is null |
| @LeftPad | length, padChar | String | Appends zeros (or other character) to the left |
| @Numeric |  | String | Returns only numbers |
| @Prefix | value, ifNotPresent | String | Adds a prefix to a String |
| @Replace | search, replacement, all, ignoreCase | String | Replaces the first or all occurrences of the search with the replacement |
| @RightPad | length, padChar | String | Appends zeros (or other character) to the right |
| @Round | scale | Double | Rounds a Double |
| @RoundDown | scale | Double | Rounds a Double down |
| @RoundUp | scale | Double | Rounds a Double up |
| @StripNewlines |  | String | Removes all new line characters |
| @StripTags |  | String | Removes all tags |
| @Suffix | value, ifNotPresent | String | Adds a suffix to a String |
| @ToLowerCase |  | String | Converts a String to lower case |
| @ToUpperCase |  | String | Converts a String to upper case |
| @Trim | stripChar | String | Removes leading and trailing whitespace (or other character) |
| @Whitelist | list | String | Returns null if the value is not present in the list |

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