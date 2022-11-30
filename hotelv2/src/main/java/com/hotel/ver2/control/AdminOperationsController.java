package com.hotel.ver2.control;

import com.hotel.ver2.dto.DbReservationDto;
import com.hotel.ver2.entity.DbReservation;
import com.hotel.ver2.repo.DbReservationRepo;
import com.hotel.ver2.service.impl.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@Slf4j
public class AdminOperationsController {
    @Autowired
    ReservationService reservationService;
@Autowired
DbReservationRepo reservationRepo;

    //doing it in 1 class bc no time
    //also I am not really interested in doing it because I will show this only 1 time in my life
    //if you are reading this and are not me, I am wrong, but Hello anyway
    @GetMapping(
            value = "/admin",
            produces = "application/json"
    )
    @ResponseBody
    public String subMenu() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }


    /////////////
    ////////////RESETVATION
    ///////////
    @GetMapping(
            value = "/admin/Reservation",
            produces = "application/json"
    )
    @ResponseBody
    public String reservationSubMenu() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }


    public AdminOperationsController() {
    }

    //  @GetMapping (
          //  value = "/admin/Reservation/CREATE",
          //  produces = "application/json"
  //  )
   // @ResponseBody
    @GetMapping("/admin/Reservation/createReservation/new")
    public String newReserv(Model model) {
        model.addAttribute("createDTO" ,new DbReservationDto() );
        return "createReservation";
    }
  @PostMapping("/admin/Reservation/createReservation")
    public String createReservation(@ModelAttribute("createDTO") DbReservationDto dbReservationDto) {
        DbReservation dbReservation = reservationService.reserveARoom(dbReservationDto);
        log.info("INTO POST");
        return "success";
    }

   /* @PutMapping(
            value = "/admin/Reservation/UPDATE",
            produces = "application/json"
    )
    @ResponseBody
    public String updateReservation(@ModelAttribute("dbReservationDto") DbReservationDto dbReservationDto) {
        DbReservation dbReservation = reservationService.updateAReservation(dbReservationDto);

        return "subReserv";
    }*/

    @DeleteMapping(
            value = "/admin/Reservation/DELETE",
            produces = "application/json"
    )
    @ResponseBody
    public String deleteReservation(@RequestParam int id) {
        reservationService.deleteARoom(id);
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    @GetMapping(
            value = "/admin/Reservation/findAllByRoomNumberAndDepart",
            produces = "application/json"
    )
    public String findAllByRoomNumberAndDepartBeforeAndStatusEquals(@RequestParam String roomNum, @RequestParam Timestamp depart,@RequestParam String status ) {
        List<DbReservation> list= reservationRepo.findAllByRoomNumberAndDepartBeforeAndStatusEquals(roomNum,depart,status);
        return "ReadReservationPage1";
    }

    @GetMapping(
            value = "/admin/Reservation/findAllByBookerIdAndStatusEquals",
            produces = "application/json"
    )
    @ResponseBody
    public String findAllByBookerIdAndStatusEquals(@RequestParam String name, @RequestParam String stat,Model model) {

        List<DbReservation> list= reservationRepo.findAllByBookerIdAndStatusEquals(name,stat);
        model.addAttribute("reservDTOs", list);
        return "ReadReservationPage1";
    }


    @GetMapping(
            value = "/admin/Reservation/findll",
            produces = "application/json"
    )

    public String findAll(Model model) {

        Iterable<DbReservation> list= reservationRepo.findAll();
        model.addAttribute("reservDTOs", list);
        return "ReadReservationPage1";
    }

    @GetMapping(
            value = "/admin/Reservation/findAllByRoomNumberAndStatusEquals",
            produces = "application/json"
    )
    @ResponseBody
    public String findAllByRoomNumberAndStatusEquals() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    /////////////
    ////////////ROOM
    ///////////
    @GetMapping(
            value = "/admin/Room",
            produces = "application/json"
    )
    @ResponseBody
    public String roomSubMenu() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }


    @PostMapping(
            value = "/admin/Room/CREATE",
            produces = "application/json"
    )
    @ResponseBody
    public String createRoom() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    @PutMapping(
            value = "/admin/Room/UPDATE",
            produces = "application/json"
    )
    @ResponseBody
    public String updateRoom() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    @GetMapping(
            value = "/admin/Room/findAllRooms",
            produces = "application/json"
    )
    @ResponseBody
    public String findAllRooms() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }
    @GetMapping(
            value = "/admin/Room/findAllByRateBetween",
            produces = "application/json"
    )
    @ResponseBody
    public String findAllByRateBetween() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }
    @GetMapping(
            value = "/admin/Room/findAllById",
            produces = "application/json"
    )
    @ResponseBody
    public String findAllById() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }
    @GetMapping(
            value = "/admin/Room/findAllBySize",
            produces = "application/json"
    )
    @ResponseBody
    public String findAllBySize() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    @DeleteMapping (
            value = "/admin/Room/DELETE",
            produces = "application/json"
    )
    @ResponseBody
    public String deleteRoom() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    /////////
    /////////Service
    /////////

    @GetMapping(
            value = "/admin/Services",
            produces = "application/json"
    )
    @ResponseBody
    public String servicesSubMenu() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }


    @PostMapping(
            value = "/admin/Services/CREATE",
            produces = "application/json"
    )
    @ResponseBody
    public String createServices() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    @PutMapping(
            value = "/admin/Services/UPDATE",
            produces = "application/json"
    )
    @ResponseBody
    public String updateServices() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    @GetMapping(
            value = "/admin/Services/findAllRooms",
            produces = "application/json"
    )
    @ResponseBody
    public String findAllServices() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }
    @DeleteMapping (
            value = "/admin/Services/DELETE",
            produces = "application/json"
    )
    @ResponseBody
    public String deleteServices() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }
    ///////
    ///////Serviced USer
    //////
    @GetMapping(
            value = "/admin/ServicedUsers",
            produces = "application/json"
    )
    @ResponseBody
    public String servicedUsersSubMenu() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }


    @PostMapping(
            value = "/admin/ServicedUsers/CREATE",
            produces = "application/json"
    )
    @ResponseBody
    public String createServicedUsers() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    @PutMapping(
            value = "/admin/ServicedUsers/UPDATE",
            produces = "application/json"
    )
    @ResponseBody
    public String updateServicedUsers() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }

    @GetMapping(
            value = "/admin/ServicedUsers/findAllRooms",
            produces = "application/json"
    )
    @ResponseBody
    public String findAllServicedUsers() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }
    @DeleteMapping (
            value = "/admin/ServicedUsers/DELETE",
            produces = "application/json"
    )
    @ResponseBody
    public String deleteServicedUsers() {
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }
}
