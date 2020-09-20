package io.redis.jedis.springredis.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.redis.jedis.springredis.model.Programer;
import io.redis.jedis.springredis.services.ProgramerServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ProgramerController {
    private final ProgramerServices programerServices;
    private final ObjectMapper objectMapper;

    public ProgramerController(ProgramerServices programerServices, ObjectMapper objectMapper){
        this.programerServices = programerServices;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/api/programer-string")
    public void addProgramer(@RequestBody Programer programer) throws JsonProcessingException {
        this.programerServices.setProgramerAsString(programer.getId(), this.objectMapper.writeValueAsString(programer));
    }

    @GetMapping("/api/programer-string/{id}")
    public Programer getProgramer(@PathVariable int id) throws JsonProcessingException {
        String programerAsString = this.programerServices.getProgramerAsString(id);
        return this.objectMapper.readValue(programerAsString, Programer.class);
    }

    // LIST
    @PostMapping("/api/programer-list")
    public void addProgramerToList(@RequestBody Programer programer)  {
        this.programerServices.addProgramgerToList(programer);
    }

    @GetMapping("/api/programer-list/all")
    public List<Programer> getProgramers()  {
        return this.programerServices.getProgramerListMembers();
    }

    @GetMapping("/api/programer-list/count")
    public Long getCountProgramers()  {
        return this.programerServices.getProgramerListCount();
    }

    // SET
    @PostMapping("/api/programer-set")
    public void addProgramersToSet(@RequestBody Programer ...programer)  {
        this.programerServices.addProgramgerToSet(programer);
    }

    @GetMapping("/api/programer-set/all")
    public Set<Programer> getProgramersInSet() {
        return this.programerServices.getProgramerSetMembers();
    }

    @GetMapping("/api/programer-set/count")
    public Long getCountProgramersInSet()  {
        return this.programerServices.getProgramerSetCount();
    }

    @GetMapping("/api/programer-set/member")
    public boolean isSetMember(@RequestBody Programer programer)  {
        return this.programerServices.isSetMember(programer);
    }

    // HASH
    @PostMapping("/api/programer-hash")
    public void addProgramersToHash(@RequestBody Programer programer)  {
        this.programerServices.saveHash(programer);
    }

    @PutMapping("/api/programer-hash")
    public void updateProgramersToHash(@RequestBody Programer programer)  {
        this.programerServices.updateHash(programer);
    }

    @GetMapping("/api/programer-hash/all")
    public Map<Integer,Programer> findAllHash() {
        return this.programerServices.findAllHash();
    }

    @GetMapping("/api/programer-hash/{id}")
    public Programer findInHash(@PathVariable int id) {
        return this.programerServices.findInHash(id);
    }

    @DeleteMapping("/api/programer-hash/{id}")
    public void deleteInHash(@PathVariable int id) {
        this.programerServices.deleteHash(id);
    }
}
