package com.tordot.tour.config.security;

import com.tordot.tour.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    UserService userService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public AuthSuccessHandler(UserService userService
                              //@Qualifier("s_roleService") RoleService roleService,
                              // @Qualifier("s_privilegeService")
                              ) {
        this.userService = userService;
//        this.roleService = roleService;
//        this.privilegeService = privilegeService;
//        this.groupeService = groupeService;
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        //###
        HttpSession session = request.getSession(true);
        ServletContext context = request.getServletContext();
        User principal = (User) authentication.getPrincipal();
        //###
//        List<Privilege> privileges = privilegeService.getListePrivilegeByUserName(principal.getUsername());
//        //###
//        String login = principal.getUsername();
//        Compte compte = null;
//        MutableInt nbr = new MutableInt(0);
//        compte = compteService.getCompte(login);
//        compte.setPrivilegeList(privileges);
///*        List<com.enageo.achat.model.Groupe> groupesUser = groupeService.getGroupesUser(login);
//        compte.setGroupeList(groupesUser);*/
//        session.setAttribute("myCompte", compte);
//        compteService.Log_Access(login, principal.getPassword(), request);
//        List<HttpSession> listSession;
//        if (context.getAttribute("listSession") == null)
//            listSession = new ArrayList<>();
//        else listSession = (List<HttpSession>) context.getAttribute("listSession");
//        listSession.add(session);
//        List<String> pl = new ArrayList<>();
//        if (compte.getListPrivilegeType() != null)
//            pl = compte.getListPrivilegeType().stream()
//                    .filter(type -> type.getListPrivilege() != null)
//                    .flatMap(type -> type.getListPrivilege().stream())
//                    .map(privilege -> Integer.toString(privilege.getId()))
//                    .collect(Collectors.toList());
///*        session.setAttribute("birtName", Settings.getHashMaps().get(Settings.BIRT_NAME).getValue());
//        session.setAttribute("BIRT_DIR", Settings.getHashMaps().get(Settings.BIRT_DIR).getValue());
//        session.setAttribute("appName", Settings.getHashMaps().get(Settings.APP_NAME).getValue());*/
//        context.setAttribute("pl", pl);//todo
//        context.setAttribute("listSession", listSession);
//        context.setAttribute("listExercices", exerciceService.getListExercices());
//        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//        if (defaultSavedRequest != null) {
//            String targetURL = defaultSavedRequest.getRedirectUrl();
//            redirectStrategy.sendRedirect(request, response, targetURL);
//        } else redirectStrategy.sendRedirect(request, response, "/accueil");
    }
}
