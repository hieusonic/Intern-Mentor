package com.example.demo2.entity.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

    @Document
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Mentor {
        @Id
        private Integer id;

        private String name;

        private String contactInformation;

        @DBRef
        private List<Intern> interns;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContactInformation() {
            return contactInformation;
        }

        public void setContactInformation(String contactInformation) {
            this.contactInformation = contactInformation;
        }

        public List<Intern> getInterns() {
            return interns;
        }

        public void setInterns(List<Intern> interns) {
            this.interns = interns;
        }
    }
