package com.hotel.ver2.control;

import com.hotel.ver2.dto.*;
import com.hotel.ver2.entity.DbReservation;
import com.hotel.ver2.entity.DbRoom;
import com.hotel.ver2.entity.DbServiceList;
import com.hotel.ver2.entity.DbServicedUser;
import com.hotel.ver2.repo.DbReservationRepo;
import com.hotel.ver2.repo.DbRoomRepo;
import com.hotel.ver2.repo.DbServiceListRepo;
import com.hotel.ver2.repo.DbServicedUserRepo;
import com.hotel.ver2.service.impl.ReservationService;
import com.hotel.ver2.service.impl.RoomsService;
import com.hotel.ver2.service.impl.ServicesService;
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

    @Autowired
    ServicesService servicesService;

    @Autowired
    DbServiceListRepo serviceListRepo;

    @Autowired
    DbServicedUserRepo dbServicedUserRepo;

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
        return "reservations/createReservation";
    }

    @GetMapping("/admin/Reservation")
    public String reservMenuForm(Model model) {
       // model.addAttribute("createDTO" ,new DbReservationDto() );
        return "reservations/subReservationMenu";
    }

  @PostMapping("/admin/Reservation/cr")
    public String createReservation(@ModelAttribute("createDTO") DbReservationDto dbReservationDto) {
        DbReservation dbReservation = reservationService.reserveARoom(dbReservationDto);
        log.info("INTO POST");
        return "reservations/subReservationMenu";
    }


  @GetMapping("/admin/Reservation/upd")
    public String updateReservForm(Model model) {
        model.addAttribute("updateDTO" ,new DbReservationDto());
        return "reservations/UpdateReservation";
    }
    //почему то он упорно не хочет видеть put реквесты
    @PostMapping("/admin/Reservation/upd")
    public String updateReservation(@ModelAttribute("updateDTO") DbReservationDto dbReservationDto) {
        log.info("INTO updateReservation");
        DbReservation dbReservation = reservationService.findReservation(dbReservationDto.getId());
        DbReservation dbReservation2 = reservationService.updateAReservation(dbReservationDto, dbReservation);
        return "reservations/subReservationMenu";
    }


    @GetMapping(
            value = "/admin/Reservation/del",
            produces = "application/json"
    )
    public String deleteReservationForm(Model model) {
        model.addAttribute("id",new IDdto());
        return "reservations/deleteReservation";
    }
//либо тмин чудит либо я не знаю что
    //мне будет очень интересно узнать почему он не может воспринимать что то кроме гет и пост но это потом сейчас мне важно показать что
    //что то работают для человека которому не интересно
    @PostMapping(
            value = "/admin/Reservation/del",
            produces = "application/json"
    )
    public String deleteReservation(@ModelAttribute("id") IDdto id) {
        reservationService.deleteARoom(Integer.valueOf(id.getId()));
        return "reservations/subReservationMenu";
    }

    @GetMapping(
            value = "/admin/Reservation/findAllByRoomNumberAndDepart",
            produces = "application/json"
    )
    public String findAllByRoomNumberAndDepartBeforeAndStatusEqualsForm(Model model) {
        model.addAttribute("reqData", new RoomNumAndDepartDTO());
        //List<DbReservation> list= reservationRepo.findAllByRoomNumberAndDepartBeforeAndStatusEquals(dto.getRoomNum(), Timestamp.valueOf(dto.getDepart()),dto.getStatus());

        return "reservations/readReservationPage1";
    }

    @GetMapping(
            value = "/admin/Reservation/findAllByRoomNumberAndDepart/table",
            produces = "application/json"
    )
    public String findAllByRoomNumberAndDepartBeforeAndStatusEquals(@ModelAttribute("reqData")RoomNumAndDepartDTO dto, Model model) {
        List<DbReservation> list= reservationRepo.findAllByRoomNumberAndDepartBeforeAndStatusEquals(dto.getRoomNum(), Timestamp.valueOf(dto.getDepart()),dto.getStatus());
        model.addAttribute("reservDTOs", list);
        return "reservations/readReservationTable1";
    }


    @GetMapping(
            value = "/admin/Reservation/findAllByBookerIdAndStatusEquals",
            produces = "application/json"
    )

    public String findAllByBookerIdAndStatusEquals(Model model) {
        model.addAttribute("reqData2", new NameStatDto());
        return "reservations/ReadReservationPage2";
    }
    @GetMapping(
            value = "/admin/Reservation/findAllByBookerIdAndStatusEquals/table",
            produces = "application/json"
    )

    public String findAllByBookerIdAndStatusEquals(@ModelAttribute("reqData2")NameStatDto dto, Model model) {
        List<DbReservation> list= reservationRepo.findAllByBookerIdAndStatusEquals(dto.getName(),dto.getStat());
        model.addAttribute("reservDTOs", list);
        return "reservations/readReservationTable1";
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
        return "reservations/readReservationTable1";
    }













    /////////////
    ////////////ROOM
    ///////////

    @GetMapping(value = "/admin/Room")
    public String roomSubMenu(Model model) {
     //   model.addAttribute("roomDto", new RoomDto());
        model.addAttribute("roomListDTOs",roomsService.showRooms(0,"byRateASC") );
        return "rooms/roomSubMenu";
    }
    @GetMapping(value = "/admin/Room/cr")
    public String createRoomForm(Model model) {
        model.addAttribute("roomDto", new RoomDto());
        return "rooms/createRoom";
    }
    @PostMapping(value = "/admin/Room/cr")
    public String createRoom(@ModelAttribute("roomDto")RoomDto roomDto) {
        DbRoom dbRoom = new DbRoom();
        dbRoom.setId(roomDto.getId());
        dbRoom.setInfo(roomDto.getInfo());
        dbRoom.setRate(BigDecimal.valueOf(Double.valueOf(roomDto.getRate())));
        dbRoom.setSize(roomDto.getSize());
        dbRoomRepo.save(dbRoom);
        return "rooms/roomSubMenu";
    }

    @GetMapping(value = "/admin/Room/upd")
    public String updateRoomForm(Model model) {
        model.addAttribute("roomUpdDto", new RoomDto());
        return "rooms/updRoom";
    }
    @PostMapping(value = "/admin/Room/upd")
    public String updateRoom(@ModelAttribute("roomUpdDto")RoomDto roomDto) {
        DbRoom dbRoom = dbRoomRepo.findById(roomDto.getId()).orElse(null);
        if( !("".equals(roomDto.getRate()))){
            dbRoom.setRate(BigDecimal.valueOf(Double.valueOf(roomDto.getRate())));
        }
        if(!("".equals(roomDto.getInfo()))){
            dbRoom.setInfo(roomDto.getInfo());
        }
        if(roomDto.getSize()!=0){
            dbRoom.setSize(roomDto.getSize());
        }
        dbRoomRepo.save(dbRoom);
        return "rooms/roomSubMenu";
    }

    @GetMapping(value = "/admin/Room/del")
    public String deleteRoomForm(Model model) {
        model.addAttribute("roomId", new IDdto());
        return "rooms/delRoom";
    }

    @PostMapping(value = "/admin/Room/del")
    public String deleteRoom(@ModelAttribute("roomId") IDdto id) {
        dbRoomRepo.deleteById(id.getId());
        return "rooms/roomSubMenu";
    }

    @GetMapping(value = "/admin/Room/rateASC")
    public String findAllByRateASC(Model model) {
        model.addAttribute("roomListDTOs",roomsService.showRooms(0,"byRateASC") );
        return "rooms/readRoomTable";
    }

    @GetMapping(value = "/admin/Room/rateDESC")
    public String findAllByRateDESC(Model model) {
        model.addAttribute("roomListDTOs",roomsService.showRooms(0,"byRateDESC") );
        return "rooms/readRoomTable";
    }

    @GetMapping(value = "/admin/Room/sizeASC")
    public String findAllBySizeASC(Model model) {
        model.addAttribute("roomListDTOs",roomsService.showRooms(0,"bySizeASC") );
        return "rooms/readRoomTable";
    }

    @GetMapping(value = "/admin/Room/sizeDESC")
    public String findAllBySizeDESC(Model model) {
        model.addAttribute("roomListDTOs",roomsService.showRooms(0,"bySizeDESC") );
        return "rooms/readRoomTable";
    }



    /////////
    /////////Service
    /////////

    @GetMapping(
            value = "/admin/Services",
            produces = "application/json"
    )

    public String servicesSubMenu() {
        return "services/servicesSubMenu";
    }

    @GetMapping(value = "/admin/Services/cr")
    public String createServicesForm(Model model) {
        model.addAttribute("servDto", new ServiceListDto());
        return "services/createService";
    }
    @PostMapping(value = "/admin/Services/cr")
    public String createServices(@ModelAttribute("servDto") ServiceListDto dto) {
        DbServiceList serviceList = new DbServiceList();

        serviceList.setPrice(BigDecimal.valueOf(Double.valueOf(dto.getPrice())));
        serviceList.setServicename(dto.getName());
        serviceListRepo.save(serviceList);
        return "services/servicesSubMenu";
    }

    @GetMapping(value = "/admin/Services/upd")
    public String updateServicesForm(Model model) {
        model.addAttribute("updServDto", new ServiceListDto());
        return "services/updService";
    }

    @PostMapping(value = "/admin/Services/upd")
    public String updateServices(@ModelAttribute("updServDto") ServiceListDto dto) {
        DbServiceList serviceList = new DbServiceList();
        serviceList.setPrice(BigDecimal.valueOf(Double.valueOf(dto.getPrice())));
        serviceList.setServicename(dto.getName());
        serviceListRepo.save(serviceList);
        return "services/servicesSubMenu";
    }

    @GetMapping (value = "/admin/Services/del")
    public String deleteServicesForm(Model model) {
        model.addAttribute("servDto", new ServiceListDto());
        return "services/delService";
    }

    @PostMapping (value = "/admin/Services/del")
    public String deleteServices(@ModelAttribute("servDto") ServiceListDto dto) {
        serviceListRepo.deleteById(dto.getName());
        return "services/servicesSubMenu";
    }

    @GetMapping(value = "/admin/Services/findByName")
    public String findServicesForm(Model model) {
        model.addAttribute("servDto", new ServiceListDto());
        return "services/findServicePage";
    }

    @PostMapping(value = "/admin/Services/findByName")
    public String findAllServicesByName(@ModelAttribute("servDto") ServiceListDto dto,Model model) {
        model.addAttribute("servTableDTOs", serviceListRepo.findById(dto.getName()).orElse(null));
        return "services/findServiceTable";
    }

    @GetMapping(value = "/admin/Services/findAll")
    public String findAllServices(Model model) {
        model.addAttribute("servTableDTOs", serviceListRepo.findAll());
        return "services/findServiceTable";
    }
    ///////
    ///////Serviced USer
    //////
    @GetMapping(value = "/admin/ServicedUsers")
    public String servicedUsersSubMenu() {
        return "servicedUsers/servicedUserSubMenu";
    }

    @GetMapping(value = "/admin/ServicedUsers/cr")
    public String createServicedUsersForm(Model model) {
        model.addAttribute("servUdto", new ServicedUserDto());
        return "servicedUsers/createServicedUser";
    }
    @PostMapping(value = "/admin/ServicedUsers/cr")
    public String createServicedUsers(@ModelAttribute("servUdto") ServicedUserDto dto) {
        DbServicedUser servicedUser = new DbServicedUser();
        servicedUser.setServicename(dto.getServicename());
        servicedUser.setUsername(dto.getUsername());
        servicedUser.setStatus(dto.getStatus());
        dbServicedUserRepo.save(servicedUser);
        return "servicedUsers/servicedUserSubMenu";
    }


   /* @GetMapping(value = "/admin/ServicedUsers/upd")
    public String updateServicedUsersForm() {
        return "servicedUsers/updateServicedUsers";
    }
    @PostMapping(value = "/admin/ServicedUsers/upd")
    public String updateServicedUsers() {
        return "servicedUsers/servicedUserSubMenu";
    }*/

    @GetMapping(value = "/admin/ServicedUsers/findAll")
    public String findAllServicedUsers(Model model) {
        model.addAttribute("servUsersTableDTOs", dbServicedUserRepo.findAll());
        return "servicedUsers/showServicedUsersTable";
    }

    @GetMapping (value = "/admin/ServicedUsers/del")
    public String deleteServicedUsersForm(Model model) {
        model.addAttribute("servUdto", new ServicedUserDto());
        return "servicedUsers/deleteServicedUsers";
    }
    @PostMapping (value = "/admin/ServicedUsers/del")
    public String deleteServicedUsers(@ModelAttribute("servUdto") ServicedUserDto dto) {
        dbServicedUserRepo.deleteById(dto.getId());
        return "servicedUsers/servicedUserSubMenu";
    }


    ////user stuff

    @GetMapping (value = "/admin")
    public String registerForm() {
        return "index";
    }

    @GetMapping (value = "/admin/register")
    public String registerForm(Model model) {
        model.addAttribute("UserDto", new UserDTO());
        return "servicedUsers/deleteServicedUsers";
    }
    @PostMapping (value = "/admin/register")
    public String register(@ModelAttribute("UserDto") UserDTO dto) {
        return "index";
    }

}
