/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.carloscortina.demo.controller;

import com.carloscortina.demo.model.Permissions;
import com.carloscortina.demo.model.Staffmember;
import com.carloscortina.demo.model.User;
import com.carloscortina.demo.service.PermissionsService;
import com.carloscortina.demo.service.StaffMemberService;
import com.carloscortina.demo.service.UserService;
import com.carloscortina.demo.service.UserroleService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Carlos Cortina
 */
@Controller
@RequestMapping(value="/administration")
public class AdministrationController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private StaffMemberService staffService;
    @Autowired
    private UserroleService roleService;
    @Autowired
    private PermissionsService permissionsService;
    
    @RequestMapping(value="userHome")
    public String userHome(Model model){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("loggedUser", userService.getUserByUsername(auth.getName()));
    
        return "Administration/Users/UserHome";
    }
    
    @RequestMapping(value="newUser")
    public String newUser(Model model){
        model.addAttribute("roles", roleService.getAll(""));
        return "Administration/Users/NewUser";
    }
    
    @RequestMapping(value="permissionsHome")
    public String permissionsHome(Model model){
        model.addAttribute("permissions",permissionsService.getAll(""));
        return "Administration/Permissions/PermissionsHome";
    }
    
    @RequestMapping(value="modifyUser")
    public @ResponseBody void modifyUser(@RequestParam Map<String,String> params){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();    
        User modifyUser = userService.getUserByUsername(auth.getName());
        Staffmember modifyStaff = modifyUser.getIdStaffMember();
        
        modifyUser.setEmail(params.get("email"));
        if( !params.get("password").isEmpty() ){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
            modifyUser.setPassword(passwordEncoder.encode(params.get("password")));
        }
        userService.updateItem(modifyUser);
        
        modifyStaff.setName(params.get("firstName"));
        modifyStaff.setLastName(params.get("lastName"));
        modifyStaff.setPhone(params.get("phone"));
        modifyStaff.setCellPhone("cellPhone");
        modifyStaff.setProfessionalNumber(params.get("professionalNumber"));
        modifyStaff.setPresciptionNumber(Integer.parseInt(params.get("prescriptionNumber")));
        modifyStaff.setReceiptNumber(Integer.parseInt(params.get("receiptsNumber")));
        staffService.updateItem(modifyStaff);
    }

    /**
     *
     * @param params
     * @return
     */
    @RequestMapping(value="addUser")
    public @ResponseBody AddUserStatus newUser(@RequestParam Map<String,String> params){
        //Get logged User
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();    
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
        
        if(userService.getUserByUsername(params.get("userName")) != null){
            AddUserStatus status = new AddUserStatus("El usuario ya existe","Error");
            return(status);
        }
        
        Staffmember staff = new Staffmember(params.get("firstName"),
                params.get("lastName"), params.get("phone"), params.get("cellPhone"),
                params.get("professionalNumber"), new Date(),
                Integer.parseInt(params.get("prescriptionNumber")),
                Integer.parseInt(params.get("receiptsNumber")));
        staffService.create(staff);
        
        User user = new User(params.get("userName"), 
                passwordEncoder.encode(params.get("password")), params.get("email"),
                new Date(), 1, staff, roleService.getById(Integer.parseInt(params.get("role"))));
        
        userService.create(user);
        
        return (new AddUserStatus("Se a agregado el usuario correctamente","Success"));
    }
    
    @RequestMapping(value="updatePermissions")
    public @ResponseBody void updatePersmissions(@RequestParam Map<String,String> params){
        
        Map<String,Boolean> modules = new HashMap<String, Boolean>();
        //Check if the modules are active (1) or not (0)
        
        modules.put("Ingresos", params.get("27").equals("1"));
        modules.put("Pacientes", params.get("28").equals("1"));
        modules.put("Diagnosticos", params.get("29").equals("1"));
        modules.put("Catalogos", params.get("30").equals("1"));
        modules.put("Reportes", params.get("31").equals("1"));
        
        Permissions current ;
        int pv =0;
        for(Map.Entry<String,String> permission: params.entrySet()){
            //Get the objet of the current permission
            current = permissionsService.getById(Integer.parseInt(permission.getKey())); 
            
            //Get the current status of the module
            if( !current.getModule().equals("Modulo") && modules.get(current.getModule())  ){
                //current permission module is active
                pv = Integer.parseInt(permission.getValue());
                
                if(current.getValue() != pv){
                    //if the value changed , update it
                    current.setValue(pv);
                    permissionsService.updateItem(current);
                }
            }
        }
        //Update modules
        for(int i=27; i<= 31; i++){
            current = permissionsService.getById(i);
            pv = Integer.parseInt(params.get(Integer.toString(i)));
            if( pv != current.getValue()){
                current.setValue(pv);
                permissionsService.updateItem(current);
            }
        }
    }        
    
    class AddUserStatus{
        private String status;
        private String msg;
        
        public AddUserStatus(String status,String msg){
            this.status=msg;
            this.msg=status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    
    }
    
}
