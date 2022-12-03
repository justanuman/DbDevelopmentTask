package com.hotel.ver2.control;

import com.hotel.ver2.dto.*;
import com.hotel.ver2.entity.DbReservation;
import com.hotel.ver2.entity.DbRoom;
import com.hotel.ver2.repo.DbReservationRepo;
import com.hotel.ver2.repo.DbRoomRepo;
import com.hotel.ver2.service.impl.ReservationService;
import com.hotel.ver2.service.impl.RoomsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Controller
@Slf4j
public class AdminOperationsController {
    @Autowired
    ReservationService reservationService;
@Autowired
DbReservationRepo reservationRepo;


    @Autowired
    RoomsService roomsService;
    @Autowired
    DbRoomRepo dbRoomRepo;
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
    @GetMapping("/admin/Reservation/cr")
    public String newReserv(Model model) {
        model.addAttribute("createDTO" ,new DbReservationDto() );
        return "createReservation";
    }

    @GetMapping("/admin/Reservation")
    public String reservMenuForm(Model model) {
       // model.addAttribute("createDTO" ,new DbReservationDto() );
        return "subReservationMenu";
    }

  @PostMapping("/admin/Reservation/cr")
    public String createReservation(@ModelAttribute("createDTO") DbReservationDto dbReservationDto) {
        DbReservation dbReservation = reservationService.reserveARoom(dbReservationDto);
        log.info("INTO POST");
        return "subReservationMenu";
    }


  @GetMapping("/admin/Reservation/upd")
    public String updateReservForm(Model model) {
        model.addAttribute("updateDTO" ,new DbReservationDto());
        return "UpdateReservation";
    }
    //почему то он упорно не хочет видеть put реквесты
    @PostMapping("/admin/Reservation/upd")
    public String updateReservation(@ModelAttribute("updateDTO") DbReservationDto dbReservationDto) {
        log.info("INTO updateReservation");
        DbReservation dbReservation = reservationService.findReservation(dbReservationDto.getId());
        DbReservation dbReservation2 = reservationService.updateAReservation(dbReservationDto, dbReservation);
        return "subReservationMenu";
    }


    @GetMapping(
            value = "/admin/Reservation/del",
            produces = "application/json"
    )
    public String deleteReservationForm(Model model) {
        model.addAttribute("id",new IDdto());
        return "deleteReservation";
    }
//либо тмин чудит либо я не знаю что
    //мне будет очень интересно узнать почему он не может воспринимать что то кроме гет и пост но это потом сейчас мне важно показать что
    //что то работают для человека которому не интересно
    @PostMapping(
            value = "/admin/Reservation/del",
            produces = "application/json"
    )
    public String deleteReservation(@ModelAttribute("id") IDdto id) {
        reservationService.deleteARoom(id.getId());
        return "subReservationMenu";
    }

    @GetMapping(
            value = "/admin/Reservation/findAllByRoomNumberAndDepart",
            produces = "application/json"
    )
    public String findAllByRoomNumberAndDepartBeforeAndStatusEqualsForm(Model model) {
        model.addAttribute("reqData", new RoomNumAndDepartDTO());
        //List<DbReservation> list= reservationRepo.findAllByRoomNumberAndDepartBeforeAndStatusEquals(dto.getRoomNum(), Timestamp.valueOf(dto.getDepart()),dto.getStatus());

        return "readReservationPage1";
    }

    @GetMapping(
            value = "/admin/Reservation/findAllByRoomNumberAndDepart/table",
            produces = "application/json"
    )
    public String findAllByRoomNumberAndDepartBeforeAndStatusEquals(@ModelAttribute("reqData")RoomNumAndDepartDTO dto, Model model) {
        List<DbReservation> list= reservationRepo.findAllByRoomNumberAndDepartBeforeAndStatusEquals(dto.getRoomNum(), Timestamp.valueOf(dto.getDepart()),dto.getStatus());
        model.addAttribute("reservDTOs", list);
        return "readReservationTable1";
    }


    @GetMapping(
            value = "/admin/Reservation/findAllByBookerIdAndStatusEquals",
            produces = "application/json"
    )

    public String findAllByBookerIdAndStatusEquals(Model model) {
        model.addAttribute("reqData2", new NameStatDto());
        return "ReadReservationPage2";
    }
    @GetMapping(
            value = "/admin/Reservation/findAllByBookerIdAndStatusEquals/table",
            produces = "application/json"
    )

    public String findAllByBookerIdAndStatusEquals(@ModelAttribute("reqData2")NameStatDto dto, Model model) {
        List<DbReservation> list= reservationRepo.findAllByBookerIdAndStatusEquals(dto.getName(),dto.getStat());
        model.addAttribute("reservDTOs", list);
        return "readReservationTable1";
    }

    //String roomNum, @RequestParam Timestamp depart,@RequestParam String status

/*    @GetMapping(
            value = "/admin/Reservation/findAllByRoomNumberAndDepart",
            produces = "application/json"
    )
    public String findAllByRoomNumberAndDepartBeforeAndStatusEquals(@RequestParam String roomNum, @RequestParam Timestamp depart,@RequestParam String status ) {
        List<DbReservation> list= reservationRepo.findAllByRoomNumberAndDepartBeforeAndStatusEquals(roomNum,depart,status);
        return "ReadReservationPage1";
    }*/

    @GetMapping(
            value = "/admin/Reservation/findAll",
            produces = "application/json"
    )

    public String findAll(Model model) {
        Iterable<DbReservation> list= reservationRepo.findAll();
        model.addAttribute("reservDTOs", list);
        return "readReservationTable1";
    }













    /////////////
    ////////////ROOM
    ///////////

    @GetMapping(
            value = "/admin/Room",
            produces = "application/json"
    )
    public String roomSubMenu(Model model) {
     //   model.addAttribute("roomDto", new RoomDto());
        return "rooms/roomSubMenu";
    }
    @GetMapping(
            value = "/admin/Room/cr",
            produces = "application/json"
    )
    public String createRoomForm(Model model) {
        model.addAttribute("roomDto", new RoomDto());
        return "rooms/createRoom";
    }
    @PostMapping(
            value = "/admin/Room/cr",
            produces = "application/json"
    )
    public String createRoom(@ModelAttribute("roomDto")RoomDto roomDto) {
        DbRoom dbRoom = new DbRoom();
        dbRoom.setId(roomDto.getId());
        dbRoom.setInfo(roomDto.getInfo());
        dbRoom.setRate(BigDecimal.valueOf(Double.valueOf(roomDto.getRate())));
        dbRoom.setSize(roomDto.getSize());
        dbRoomRepo.save(dbRoom);
        return "rooms/createRoom";
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
