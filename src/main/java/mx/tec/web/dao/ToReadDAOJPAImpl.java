package mx.tec.web.dao;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import mx.tec.web.mapper.Mapper;
import mx.tec.web.repository.ToReadListRepository;
import mx.tec.web.vo.ToReadVO;

@Component
public class ToReadDAOJPAImpl implements ToReadDAO {

    @Resource
    private ToReadListRepository toReadListRepository;

    @Resource
    private Mapper mapper;

    /** {@inheritDoc} */
    @Override
    public Optional<ToReadVO> findToReadById(long id) {
        return mapper.convertToReadToOptionalVO(toReadListRepository.findById(id));
    }

    /** {@inheritDoc} */
    @Override
    public ToReadVO insertToRead(ToReadVO newToRead) {
        return mapper.convertToReadToVO(toReadListRepository.save(mapper.convertToReadToEntity(newToRead)));
    }
    
    /** {@inheritDoc} */
    @Override
    public void removeToRead(ToReadVO existingToRead) {
        toReadListRepository.delete(mapper.convertToReadToEntity(existingToRead));
    }

    /** {@inheritDoc} */
	@Override
	public List<ToReadVO> getToReadList(long userId) {
		return mapper.convertToReadToVOList(toReadListRepository.getToReadList(userId));
	}

    
}
