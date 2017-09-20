package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by holhosa on 2017.09.19..
 */
@Getter
@Setter
public class CarPark implements Serializable {

    public static List<CarPark> carParks = new ArrayList<>();

    private Long id;

    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private String type;

    private Address address;

    @Column(precision = 4)
    private Integer parkingFee;

    @Column(precision = 4)
    private Integer size;

    @Column(precision = 8, scale = 6)
    private Double xcoordinate;

    @Column(precision = 8, scale = 6)
    private Double ycoordinate;
}