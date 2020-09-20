package io.redis.jedis.springredis.dao;

import io.redis.jedis.springredis.model.Programer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class ProgramerRepositoryImpl implements ProgramerRepository {

    private final RedisTemplate redisTemplate;
    public static final String PROGRAMER_LIST_KEY = "PROGRAMER_LIST_KEY";
    private final ListOperations<String, Programer> listOperations;

    public static final String PROGRAMER_SET_KEY = "PROGRAMER_SET_KEY";
    private final SetOperations<String, Programer> setOperations;

    public static final String PROGRAMER_HASH_KEY = "PROGRAMER_HASH_KEY";
    private final HashOperations<String, Integer, Programer> hashOperations;

    public ProgramerRepositoryImpl(RedisTemplate redisTemplate,
                                   @Qualifier("listOperations") ListOperations<String, Programer> listOperations,
                                   @Qualifier("setOperations") SetOperations<String, Programer> setOperations,
                                   @Qualifier("hashOperations") HashOperations<String, Integer, Programer> hashOperations){
        this.redisTemplate = redisTemplate;
        this.listOperations = listOperations;
        this.setOperations = setOperations;
        this.hashOperations = hashOperations;
    }

    @Override
    public void setProgramerAsString(int id, String programer) {
        this.redisTemplate.opsForValue().set(id, programer);
        this.redisTemplate.expire(id, 20, TimeUnit.SECONDS);

    }

    @Override
    public String getProgramerAsString(int id) {
        return (String) this.redisTemplate.opsForValue().get(id);
    }

    //*** LIST
    @Override
    public void addProgramgerToList(Programer programer) {
        this.listOperations.leftPush(PROGRAMER_LIST_KEY, programer);

    }

    @Override
    public List<Programer> getProgramerListMembers() {
        return this.listOperations.range(PROGRAMER_LIST_KEY,0, -1);
    }

    @Override
    public Long getProgramerListCount() {
        return this.listOperations.size(PROGRAMER_LIST_KEY);
    }

    // SET
    @Override
    public void addProgramgerToSet(Programer ...programer) {
        this.setOperations.add(PROGRAMER_SET_KEY, programer);
    }

    @Override
    public Set<Programer> getProgramerSetMembers() {
        return this.setOperations.members(PROGRAMER_SET_KEY);
    }

    @Override
    public Long getProgramerSetCount() {
        return this.setOperations.size(PROGRAMER_SET_KEY);
    }

    @Override
    public boolean isSetMember(Programer programer) {
        return this.setOperations.isMember(PROGRAMER_SET_KEY, programer);
    }

    // Hash
    @Override
    public void saveHash(Programer programer) {
        this.hashOperations.put(PROGRAMER_HASH_KEY, programer.getId(), programer);
    }

    @Override
    public void updateHash(Programer programer) {
        this.hashOperations.put(PROGRAMER_HASH_KEY, programer.getId(), programer);
    }

    @Override
    public Map<Integer, Programer> findAllHash() {
        return this.hashOperations.entries(PROGRAMER_HASH_KEY);
    }

    @Override
    public Programer findInHash(int id) {
        return this.hashOperations.get(PROGRAMER_HASH_KEY, id);
    }

    @Override
    public void deleteHash(int id) {
        this.hashOperations.delete(PROGRAMER_HASH_KEY, id);
    }


}
