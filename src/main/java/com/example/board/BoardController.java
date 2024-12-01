package com.example.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/board/list", method = RequestMethod.GET)
    public String boardList(Model model){
        model.addAttribute("totalcnt", boardService.getTotalcnt());
        model.addAttribute("list", boardService.getBoardList());
        return "posts";
    }

    @RequestMapping(value = "/board/add", method = RequestMethod.GET)
    public String boardAdd(){
        return "addpostform";
    }

    @RequestMapping(value = "/board/addok", method = RequestMethod.POST)
    public String boardAddOK(BoardVO vo){
        int i = boardService.insertBoard(vo);
        if(i == 0)
            System.out.println("데이터 추가 실패!");
        else
            System.out.println("데이터 추가 성공!");
        return "redirect:list";
    }
    @RequestMapping(value = "/board/edit/{id}", method = RequestMethod.GET)
    public String boardEdit(@PathVariable("id") Integer id, Model model){
        BoardVO vo = boardService.getBoard(id);
        model.addAttribute("boardVO", vo);
        return "editform";
    }

    @RequestMapping(value = "/board/editok", method = RequestMethod.POST)
    public String boardEditOk(BoardVO vo){
        int i = boardService.updateBoard(vo);
        if(i == 0)
            System.out.println("데이터 추가 실패!");
        else
            System.out.println("데이터 추가 성공!");
        return "redirect:list";
    }

    @RequestMapping(value = "/board/delete/{id}", method = RequestMethod.GET)
    public String boardDelete(@PathVariable("id") Integer id){
        int i = boardService.deleteBoard(id);

        return "redirect:../list";
    }
}
