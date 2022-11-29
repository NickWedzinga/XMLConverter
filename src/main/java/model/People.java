package model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "people")
public class People {
    @JacksonXmlProperty(localName = "person")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Person> people;

    public People(List<Person> people) {
        this.people = people;
    }

    public List<Person> getPeople() {
        return people;
    }
}
