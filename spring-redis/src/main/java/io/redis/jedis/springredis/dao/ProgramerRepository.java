package io.redis.jedis.springredis.dao;

import io.redis.jedis.springredis.model.Programer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProgramerRepository {
    // String
    void setProgramerAsString(int id, String programer);
    String getProgramerAsString(int id);

    // List
    void addProgramgerToList(Programer programer);
    List<Programer> getProgramerListMembers();
    Long getProgramerListCount();

    // Set
    void addProgramgerToSet(Programer ...programer);
    Set<Programer> getProgramerSetMembers();
    Long getProgramerSetCount();
    boolean isSetMember(Programer programer);

    // Hash
    void saveHash(Programer programer);
    void updateHash(Programer programer);
    Map<Integer, Programer> findAllHash();
    Programer findInHash(int id);
    void deleteHash(int id);
}
