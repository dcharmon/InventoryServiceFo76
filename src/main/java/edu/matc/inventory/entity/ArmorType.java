package edu.matc.inventory.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "armor_type")
public class ArmorType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "armor_type_id")
    private int id;

    @Column(name = "type_name")
    private String typeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}