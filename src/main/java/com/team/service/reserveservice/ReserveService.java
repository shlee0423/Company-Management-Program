package com.team.service.reserveservice;

import com.team.domain.ReservationDTO;

import java.util.List;

public interface ReserveService {
    void insertReservation(ReservationDTO reservationDTO);

    List<ReservationDTO> selectReservations(String query);

    ReservationDTO selectReservationByNo(Integer reservationNo);

    void reservationApprove(Integer reservationNo);

    void deleteReservation(Integer reservationNo);
}
