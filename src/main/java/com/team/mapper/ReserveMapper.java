package com.team.mapper;

import com.team.domain.ProductDTO;
import com.team.domain.ReservationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface ReserveMapper {
    //  대여 관련
    void insertReservation(ReservationDTO reservationDTO);
    List<ReservationDTO> selectReservations(@Param("query") String query);
    ReservationDTO selectReservationByNo(Integer reservationNo);
    void ReservationUpdate(Integer reservationNo);
    void deleteReservation(Integer reservationNo);
    Integer countReservation(Integer productNo);
}
