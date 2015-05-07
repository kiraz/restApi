package com.renobidz.store.common.util;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;
import java.util.Map;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.renobidz.store.common.exception.DatabaseException;
import com.renobidz.store.entity.Address;
import com.renobidz.store.entity.Cart;
import com.renobidz.store.entity.Category;
import com.renobidz.store.entity.City;
import com.renobidz.store.entity.Comment;
import com.renobidz.store.entity.Company;
import com.renobidz.store.entity.CreditCard;
import com.renobidz.store.entity.IdeaBook;
import com.renobidz.store.entity.Portfolio;
import com.renobidz.store.entity.Product;
import com.renobidz.store.entity.Question;
import com.renobidz.store.entity.Service;
import com.renobidz.store.entity.StdProject;
import com.renobidz.store.entity.Supplier;
import com.renobidz.store.entity.Transaction;
import com.renobidz.store.entity.User;

public abstract class AbstractDAO {

	static {
		ObjectifyService.register(Address.class);
        ObjectifyService.register(Cart.class);
        ObjectifyService.register(Category.class);
        ObjectifyService.register(City.class);
        ObjectifyService.register(Comment.class);
		ObjectifyService.register(Company.class);
        ObjectifyService.register(CreditCard.class);
        ObjectifyService.register(IdeaBook.class);
		ObjectifyService.register(Portfolio.class);
        ObjectifyService.register(Product.class);
        ObjectifyService.register(Question.class);
        ObjectifyService.register(Service.class);
        ObjectifyService.register(StdProject.class);
        ObjectifyService.register(Supplier.class);
        ObjectifyService.register(Transaction.class);
        ObjectifyService.register(User.class);
    }

	public <T> void create(T t) {
		ofy().save().entity(t).now();
	}

	public <T> String createWithKey(T t) {
		Key<T> key = ofy().save().entity(t).now();
		return key.getRaw().getName();
	}

	public <T> Map<Key<T>, T> createListWithKeys(List<T> t) {
		Map<Key<T>, T> map = ofy().save().entities(t).now();
		return map;
	}

	public <T> Long createWithID(T t) {
		Key<T> key = ofy().save().entity(t).now();
		return key.getId();
	}

	public <T> void update(Class<T> clazz, Long id, T t)
			throws DatabaseException {
		if (id == null) {
			throw new DatabaseException("ID cannot be null");
		}
		ofy().save().entity(t).now();
	}

	public <T> void update(Class<T> clazz, String key, T t)
			throws DatabaseException {
		if (key == null) {
			throw new DatabaseException("ID cannot be null");
		}
		ofy().save().entity(t).now();
	}

	public <T> T getById(Class<T> clazz, Long id) throws DatabaseException {
		if (id == null) {
			throw new DatabaseException("ID cannot be null");
		}
		return ofy().load().type(clazz).id(id).now();
	}

	public <T> T getByKey(Class<T> clazz, String key) throws DatabaseException {
		if (key == null) {
			throw new DatabaseException("ID cannot be null");
		}
		return (T) ofy().load().type(clazz).id(key).now();
	}

	public <T> List<T> list(Class<T> clazz) {
		List<T> list = ofy().load().type(clazz).list();
		return list;
	}

	public <T> List<T> listOrdered(Class<T> clazz, String sortOrder,
			String fieldName) {
		List<T> list = ofy().load().type(clazz).order(sortOrder + fieldName)
				.list();
		return list;
	}

	public <T> void delete(Class<T> clazz, Long id) throws DatabaseException {
		if (id == null) {
			throw new DatabaseException("ID cannot be null");
		}
		ofy().delete().type(clazz).id(id).now();
	}

	public <T> void deleteByKey(Class<T> clazz, String key)
			throws DatabaseException {
		if (key == null) {
			throw new DatabaseException("ID cannot be null");
		}
		ofy().delete().type(clazz).id(key).now();
	}
}
