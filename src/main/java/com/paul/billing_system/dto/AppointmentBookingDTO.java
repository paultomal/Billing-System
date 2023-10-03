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

    private Long patientId;

    private String patientName;

    private String patientContact;

    @NotNull
    private Long doc_id;

    private String doc_name;

    private String consultationFee;

    private String discount;

    private String slot;

    @NotNull
    private Double totalFees;

    public static AppointmentBookingDTO form(AppointmentBooking appointmentBooking){
        if(appointmentBooking == null)
            return null;

        AppointmentBookingDTO appointmentBookingDTO = new AppointmentBookingDTO();
        appointmentBookingDTO.setId(appointmentBooking.getId());
        appointmentBookingDTO.setOrgId(appointmentBooking.getOrganization().getId());
        appointmentBookingDTO.setPatientId(appointmentBooking.getPatient().getId());
        appointmentBookingDTO.setPatientName(appointmentBooking.getPatient().getName());
        appointmentBookingDTO.setPatientContact(appointmentBooking.getPatient().getContact());
        appointmentBookingDTO.setDoc_id(appointmentBooking.getDoctor().getId());
        appointmentBookingDTO.setDoc_name(appointmentBooking.getDoctor().getName());
        appointmentBookingDTO.setConsultationFee(appointmentBooking.getDoctor().getConsultationFee());
        appointmentBookingDTO.setDiscount(appointmentBooking.getDiscount());
        appointmentBookingDTO.setSlot(appointmentBooking.getSlot());
        appointmentBookingDTO.setTotalFees(appointmentBooking.getTotalFees());

        return appointmentBookingDTO;
    }

}
