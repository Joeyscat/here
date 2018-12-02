package fun.oook.here.repository;


import fun.oook.here.entity.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PositionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionRepositoryTest.class);

    @Autowired
    private PositionRepository positionRepository;


    @Test
    public void testSave() {
        Position position = new Position();
        position.setName("home");
        position.setAddress("");
        position.setLng("345");
        position.setLat("12");

        Long newPositionId = positionRepository.save(position).getId();

        LOGGER.info("new position id {}", newPositionId);
    }

    @Test
    public void testUpdate() {
        Position position = new Position();
        position.setId(1L);
        position.setName("home1");
        position.setAddress("");
        position.setLng("345");
        position.setLat("12");

        Long newPositionId = positionRepository.save(position).getId();

        LOGGER.info("ipdated position id {}", newPositionId);
    }

    @Test
    public void testFindAll() {

        boolean empty = positionRepository.findAll().isEmpty();

        LOGGER.info("found position {}", !empty);
    }
}