package com.learnjava.optimization;

import lombok.Data;
import org.springframework.cglib.beans.BeanCopier;

/**
 * 经过测试，BeanCopier性能是BeanUtils10倍左右。
 * <p>
 * BeanCopier拷贝速度快，性能瓶颈出现在创建BeanCopier实例的过程中。 所以，把创建过的BeanCopier实例放到缓存中，下次可以直接获取，提升性能：
 *
 * @author lhy
 * @date 2021/7/21
 */
public class BeanCopierDemo {

    private static final BeanCopier BEAN_COPIER = BeanCopier.create(Person.class, PersonVo.class, false);

    public static void main(String[] args) {
        Person person = new Person("zs", "high School", 16, 177, 126);
        PersonVo vo = new PersonVo();

        BEAN_COPIER.copy(person, vo, null);

        System.out.println(vo);
    }

    @Data
    public static class PersonVo {
        private String name;
        private String grade;
        private Integer age;
        private Integer height;
        private Integer weight;

        @Override
        public String toString() {
            return "PersonVo{" +
                    "name='" + name + '\'' +
                    ", grade='" + grade + '\'' +
                    ", age=" + age +
                    ", height=" + height +
                    ", weight=" + weight +
                    '}';
        }

    }


    @Data
    public static class Person {
        private String name;
        private String grade;
        private Integer age;
        private Integer height;
        private Integer weight;

        public Person(String name, String grade, Integer age, Integer height, Integer weight) {
            this.name = name;
            this.grade = grade;
            this.age = age;
            this.height = height;
            this.weight = weight;
        }

    }
}
