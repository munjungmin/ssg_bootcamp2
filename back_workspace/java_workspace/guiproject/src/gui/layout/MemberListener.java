package gui.layout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class MemberListener implements ActionListener{
    JoinForm joinForm; 
    // public MemberListener(JoinForm joinForm, Button bt_login, Button bt_join){
    //     this.bt_login = bt_login;
    //     this.bt_join = bt_join;
    //     this.joinForm = joinForm;
    // }
    public MemberListener(JoinForm joinForm){
        this.joinForm = joinForm;
    }

    //오버라이딩
    public void actionPerformed(ActionEvent e){
        //e.getSource() 이벤트를 일으킨 컴포넌트가 Object 형으로 반환 
        // ActionEvent는 버튼만 일으킬 수 있는게 아니라 클릭이 가능한 거의 모든 컴포넌트가 Action이 적용될 수 있다. 
        // 따라서 Button에 국한되지 않는 상위자료형으로 받아버린다.
        if(e.getSource() == joinForm.bt_login){
            System.out.println("로그인 버튼 누름");
            joinForm.checkForm();
            
        }else if(e.getSource() == joinForm.bt_join){
            System.out.println("회원 가입 버튼 누름");
            joinForm.checkForm();
        }
    }

}