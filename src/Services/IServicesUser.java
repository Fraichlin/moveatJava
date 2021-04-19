/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Enitities.Coach;
import Enitities.Member;
import java.util.List;

/**
 *
 * @author NGONGANG Loic F
 */
public interface IServicesUser {
     public void addCoach(Coach c);
     public List<Coach> listCoach();
      public List<Coach> listDemandCoach();
     public void addMember(Member m);
     public List<Member> listMember(); 
     public void deleteUser(int idUser);
     public void updateMember(Member m);
     public void updateCoach(Coach c);
     public void blockUser(int idUser);
     public void unblockUser(int idUser);
     public void validCoach(int idCoach);
    
}
