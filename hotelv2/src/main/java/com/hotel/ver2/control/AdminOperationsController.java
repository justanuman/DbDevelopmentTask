package com.hotel.ver2.control;

import com.hotel.ver2.dto.*;
import com.hotel.ver2.entity.*;
import com.hotel.ver2.repo.*;
import com.hotel.ver2.service.impl.ReservationService;
import com.hotel.ver2.service.impl.RoomsService;
import com.hotel.ver2.service.impl.ServicesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@Slf4j
@Transactional
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
    //???????????? ???? ???? ???????????? ???? ?????????? ???????????? put ????????????????
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
//???????? ???????? ?????????? ???????? ?? ???? ???????? ??????
    //?????? ?????????? ?????????? ?????????????????? ???????????? ???????????? ???? ???? ?????????? ???????????????????????? ?????? ???? ?????????? ?????? ?? ???????? ???? ?????? ?????????? ???????????? ?????? ?????????? ???????????????? ??????
    //?????? ???? ???????????????? ?????? ???????????????? ???????????????? ???? ??????????????????
    @PostMapping(
            value = "/admin/Reservation/del",
            produces = "application/json"
    )
    public String deleteReservation(@ModelAttribute("id") IDdto id) {
        reservationService.deleteARoom(Integer.valueOf(id.getId()));
        return "reservations/subReservationMenu";
    }

    @GetMapping(
            value = "/admin/Reservation/findAllByRoomNumberAndDepart"
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
            value = "/admin/Reservation/findAll"
    )
    public String findAllReserv(Model model) {
        Iterable<DbReservation> list= reservationRepo.findAll();
        model.addAttribute("reservDTOs", list);
        return "reservations/readReservationTable1";
    }
    @GetMapping(
            value = "/admin/Reservation/findAllByBookerIdAndStatusEquals"
    )

    public String findAllByBookerIdAndStatusEqualsForm(Model model) {
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

    @GetMapping("/admin/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "register";
    }
@Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    DbUserRepo dbUserRepo;

    @PostMapping("/admin/registration")
    public String showRegistration(@ModelAttribute("user") UserDTO dto) {
        UserDTO userDto = new UserDTO();
        DbUser dbUser = DbUser.builder()
                .password(passwordEncoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .firstname(dto.getFirstName())
                .lastname(dto.getLastname())
                .addr(dto.getAddr())
                .bill(BigDecimal.ZERO)
                .status("OPEN")
                .build();
        dbUserRepo.save(dbUser);
        log.info("1111111111111111 saved");
        return "redirect:/admin";
    }


    @GetMapping("/admin/login")
    public String showLoginForm(WebRequest request, Model model) {
        UserDTO userDto = new UserDTO();
        model.addAttribute("user", userDto);
        return "login";
    }

    @GetMapping("/")
    public String secondIndex() {
        return "index";
    }

@Autowired
AuthenticationManagerBuilder authentication;
    @PostMapping("/admin/login")
    public String showLogin(@ModelAttribute("user") UserDTO dto) {
        return "redirect:/admin/";
    }

    @GetMapping("/admin/procedure")
    public String showProcedure(Model model)
    {
        List<String> list =(dbUserRepo.getBillsOfUsers());
       List<UserDTO> list2 = new ArrayList<>();
        for(int i = 0; i<list.size();i=i+1)
        {
            UserDTO dto = new UserDTO();
            String[] strings= list.get(i).split(",");
            dto.setFirstName(strings[0]);
            dto.setLastname(strings[1]);
            dto.setAddr(strings[2]);
            dto.setBill(strings[3]);
            list2.add(dto);
        }
        model.addAttribute("userDtos", list2);
        return "userTable";
    }
    @GetMapping("/admin/Reservation/calculateReservationPrice")

    public String showRateCalcForm(Model model)
    {
        model.addAttribute("rateInfo", new DbReservationDto());
        return "reservations/rateCalcFrom";
    }
    @PostMapping("/admin/Reservation/calculateReservationPrice")
    public String showRateCalcForm(@ModelAttribute("rateInfo") DbReservationDto dbReservationDto)
    {
        DbRoom room = dbRoomRepo.findById(dbReservationDto.getRoomNumber()).orElse(null);
        DbReservation dbReservation= reservationRepo.findByBookerIdAndArrivalAndDepartAndRoomNumber(dbReservationDto.getBookerID(),
                Timestamp.valueOf(dbReservationDto.getArrival()),
                Timestamp.valueOf(dbReservationDto.getDepart()),
                dbReservationDto.getRoomNumber()).get(0);
        String result = String.valueOf(dbRoomRepo.rateCalc(room.getRate(), dbReservation.getArrival(),dbReservation.getDepart()).abs());
        return result;
    }
}
