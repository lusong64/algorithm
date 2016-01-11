package org.song.java8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Purpose of this class is to
 */
public class TopologicalSortCourseSchedule {
    private static enum VisitStatus{
        UnVisited, Visiting, Visted;
    }

    private static int FOUND_LOOP=-1;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <=0){
            return null;
        }

        int [] result = new int[numCourses];
        if (prerequisites == null || prerequisites.length == 0){
            for (int i=0; i<result.length; i++){
                result[i] = i;
            }
            return result;
        }

        Course[] courses = new Course[numCourses];
        for (int i=0; i< courses.length; i++){
            courses[i] = new Course(i);
        }

        for (int [] preq : prerequisites){
            courses[preq[1]].addDependent(courses[preq[0]]);
        }

        int rank=numCourses;

        for (Course c : courses){
            if (c.status == VisitStatus.UnVisited){
                rank = dfs(c, rank);
                if (rank == FOUND_LOOP){
                    return new int[0];
                }
            }
        }

        Arrays.sort(courses, (c1, c2)-> c1.rank - c2.rank);

        for (int i=0; i< result.length; i++){
            result[i] = courses[i].id;
        }

        return result;
    }

    private int dfs (Course c, int rank){
        c.status=VisitStatus.Visiting;
        for (Course cc : c.dependents){
            if (cc.status == VisitStatus.Visiting){
                return FOUND_LOOP;
            }
            else if (cc.status != VisitStatus.Visted){
                rank = dfs(cc, rank);
                if (rank == FOUND_LOOP){
                    return FOUND_LOOP;
                }
            }
        }
        c.rank = rank;
        c.status = VisitStatus.Visted;
        return --rank;
    }

    private static class Course{
        private final int id;
        private final List<Course> dependents = new LinkedList<>();
        private VisitStatus status = VisitStatus.UnVisited;
        private int rank;
        public Course(int id){
            this.id = id;
        }

        public void addDependent(Course c){
            dependents.add(c);
        }
    }

    public static void main(String[] args){
        TopologicalSortCourseSchedule topologicalSortCourseSchedule = new TopologicalSortCourseSchedule();
        int[][]  preq = {{1,0},{2,0},{3,1},{3,2}};
        int [] result = topologicalSortCourseSchedule.findOrder(4, preq);
        //int[][]  preq = {{0,1}, {1,0}};
        //int [] result = topologicalSortCourseSchedule.findOrder(2, preq);
        System.out.println(Arrays.toString(result));
    }


}
