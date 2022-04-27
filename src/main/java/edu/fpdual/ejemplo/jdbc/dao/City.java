package edu.fpdual.ejemplo.jdbc.dao;

import lombok.*;

import java.math.BigInteger;
import java.sql.ResultSet;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
public class City {

    private int id;
    private String name;
    private String countryCode;
    private String district;
    private BigInteger population;

    public City(ResultSet rs){

    }

}
