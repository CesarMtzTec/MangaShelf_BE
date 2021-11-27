package mx.tec.web.dao;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import mx.tec.web.repository.MangaRepository;
import mx.tec.web.vo.MangaVO;
import mx.tec.web.mapper.Mapper;


/**
 * JPA Implementation for Data Access Object
 */
@Component("jpa")
public class MangaDAOJPAImpl implements MangaDAO{
    /** A reference to the Repository */
    @Resource
    private MangaRepository repository;

    /** A reference to the Product Mapper */
    @Resource
    private Mapper mapper;
    
    /** {@inheritDoc} */
    public List<MangaVO> findAllMangas() {
    	return mapper.convertMangaToVOList(repository.findAll());
    }
    
    /** {@inheritDoc} */
    @Override
    public Optional<MangaVO> findMangaById(long id) {
    	return mapper.convertMangaToOptionalVO(repository.findById(id));
    }
    
    /** {@inheritDoc} */
    @Override
    public MangaVO insertManga(MangaVO newManga) {
    	return mapper.convertMangaToVO(repository.save(mapper.convertMangaToEntity(newManga)));
    }
    
    /** {@inheritDoc} */
    @Override
    public void removeManga(MangaVO existingManga) {
    	repository.delete(mapper.convertMangaToEntity(existingManga));
    }
    
    /** {@inheritDoc} */
    @Override
    public void updateManga(MangaVO existingManga) {
    	repository.save(mapper.convertMangaToEntity(existingManga));
    }
}