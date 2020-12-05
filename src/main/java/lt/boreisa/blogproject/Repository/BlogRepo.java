package lt.boreisa.blogproject.Repository;

import lt.boreisa.blogproject.Model.BlogModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends CrudRepository <BlogModel, Long> {
}
