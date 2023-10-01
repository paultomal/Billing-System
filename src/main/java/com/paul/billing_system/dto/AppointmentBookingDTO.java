package com.paul.billing_system.dto;

import com.paul.billing_system.entity.AppointmentBooking;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentBookingDTO {
    private Long id;

    @NotNull
    private Long orgId;

    private Long p_id;

    private String p_name;

    private String contact;

    @NotNull
    private Long doc_id;

    private String doc_name;

    private String consultationFee;

    private String discount;

    @NotNull
    private Double totalFees;

    public static AppointmentBookingDTO form(AppointmentBooking appointmentBooking){
        if(appointmentBooking == null)
            return null;

        AppointmentBookingDTO appointmentBookingDTO = new AppointmentBookingDTO();
        appointmentBookingDTO.setId(appointmentBooking.getId());
        appointmentBookingDTO.setOrgId(appointmentBooking.getOrganization().getId());
        appointmentBookingDTO.setP_id(appointmentBooking.getPatient().getId());
        appointmentBookingDTO.setP_name(appointmentBooking.getPatient().getName());
        appointmentBookingDTO.setContact(appointmentBooking.getPatient().getContact());
        appointmentBookingDTO.setDoc_id(appointmentBooking.getDoctor().getId());
        appointmentBookingDTO.setDoc_name(appointmentBooking.getDoctor().getName());
        appointmentBookingDTO.setConsultationFee(appointmentBooking.getDoctor().getConsultationFee());
        appointmentBookingDTO.setDiscount(appointmentBooking.getDiscount());
        appointmentBookingDTO.setTotalFees(appointmentBooking.getTotalFees());

        return appointmentBookingDTO;
    }

}
