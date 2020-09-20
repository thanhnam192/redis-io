package io.redis.jedis.springredis.services;

import io.redis.jedis.springredis.dao.ProgramerRepository;
import io.redis.jedis.springredis.model.Programer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class ProgramerServicesImpl implements  ProgramerServices {

    private final ProgramerRepository programerRepository;

    public ProgramerServicesImpl(ProgramerRepository programerRepository){
        this.programerRepository = programerRepository;
    }

    @Override
    public void setProgramerAsString(int id, String programer) {
        this.programerRepository.setProgramerAsString(id, programer);
    }

    @Override
    public String getProgramerAsString(int id) {
        return this.programerRepository.getProgramerAsString(id);
    }


    // LIST
    @Override
    public void addProgramgerToList(Programer programer) {
        this.programerRepository.addProgramgerToList(programer);
    }

    @Override
    public List<Programer> getProgramerListMembers() {
        return this.programerRepository.getProgramerListMembers();
    }

    @Override
    public Long getProgramerListCount() {
        return this.programerRepository.getProgramerListCount();
    }

    // SET
    @Override
    public void addProgramgerToSet(Programer ...programer) {
        this.programerRepository.addProgramgerToSet(programer);
    }

    @Override
    public Set<Programer> getProgramerSetMembers() {
        return this.programerRepository.getProgramerSetMembers();
    }

    @Override
    public Long getProgramerSetCount() {
        return this.programerRepository.getProgramerSetCount();
    }

    @Override
    public boolean isSetMember(Programer programer) {
        return this.programerRepository.isSetMember(programer);
    }

    // HASH
    @Override
    public void saveHash(Programer programer) {
        this.programerRepository.saveHash(programer);
    }

    @Override
    public void updateHash(Programer programer) {
        this.programerRepository.updateHash(programer);
    }

    @Override
    public Map<Integer, Programer> findAllHash() {
        return this.programerRepository.findAllHash();
    }

    @Override
    public Programer findInHash(int id) {
        return this.programerRepository.findInHash(id);
    }

    @Override
    public void deleteHash(int id) {
        this.programerRepository.deleteHash(id);
    }


}
