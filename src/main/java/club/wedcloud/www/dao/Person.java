package club.wedcloud.www.dao;

import java.util.List;

public class Person {
    private Long log_id;
    private Integer person_num;
    private List<Person_info> person_info;

    public Long getLog_id() {
        return log_id;
    }

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    public Integer getPerson_num() {
        return person_num;
    }

    public void setPerson_num(Integer person_num) {
        this.person_num = person_num;
    }

    public List<Person_info> getPerson_info() {
        return person_info;
    }

    public void setPerson_info(List<Person_info> person_info) {
        this.person_info = person_info;
    }

    public class Person_info {
        private Body_parts body_parts;
        private Location location;

        public Body_parts getBody_parts() {
            return body_parts;
        }

        public void setBody_parts(Body_parts body_parts) {
            this.body_parts = body_parts;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public class Location {
            private double score;
            private double top;
            private double left;
            private double width;
            private double height;

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public double getTop() {
                return top;
            }

            public void setTop(double top) {
                this.top = top;
            }

            public double getLeft() {
                return left;
            }

            public void setLeft(double left) {
                this.left = left;
            }

            public double getWidth() {
                return width;
            }

            public void setWidth(double width) {
                this.width = width;
            }

            public double getHeight() {
                return height;
            }

            public void setHeight(double height) {
                this.height = height;
            }
        }

        public class Body_parts {
            private Coordinate nose;
            private Coordinate right_knee;
            private Coordinate left_hip;
            private Coordinate right_ankle;
            private Coordinate right_wrist;
            private Coordinate left_eye;
            private Coordinate left_mouth_corner;
            private Coordinate right_elbow;
            private Coordinate left_knee;
            private Coordinate top_head;
            private Coordinate neck;
            private Coordinate right_ear;
            private Coordinate left_ear;
            private Coordinate left_elbow;
            private Coordinate right_shoulder;
            private Coordinate right_mouth_corner;
            private Coordinate right_eye;
            private Coordinate left_ankle;
            private Coordinate right_hip;
            private Coordinate left_wrist;
            private Coordinate left_shoulder;

            public Coordinate getNose() {
                return nose;
            }

            public void setNose(Coordinate nose) {
                this.nose = nose;
            }

            public Coordinate getRight_knee() {
                return right_knee;
            }

            public void setRight_knee(Coordinate right_knee) {
                this.right_knee = right_knee;
            }

            public Coordinate getLeft_hip() {
                return left_hip;
            }

            public void setLeft_hip(Coordinate left_hip) {
                this.left_hip = left_hip;
            }

            public Coordinate getRight_ankle() {
                return right_ankle;
            }

            public void setRight_ankle(Coordinate right_ankle) {
                this.right_ankle = right_ankle;
            }

            public Coordinate getRight_wrist() {
                return right_wrist;
            }

            public void setRight_wrist(Coordinate right_wrist) {
                this.right_wrist = right_wrist;
            }

            public Coordinate getLeft_eye() {
                return left_eye;
            }

            public void setLeft_eye(Coordinate left_eye) {
                this.left_eye = left_eye;
            }

            public Coordinate getLeft_mouth_corner() {
                return left_mouth_corner;
            }

            public void setLeft_mouth_corner(Coordinate left_mouth_corner) {
                this.left_mouth_corner = left_mouth_corner;
            }

            public Coordinate getRight_elbow() {
                return right_elbow;
            }

            public void setRight_elbow(Coordinate right_elbow) {
                this.right_elbow = right_elbow;
            }

            public Coordinate getLeft_knee() {
                return left_knee;
            }

            public void setLeft_knee(Coordinate left_knee) {
                this.left_knee = left_knee;
            }

            public Coordinate getTop_head() {
                return top_head;
            }

            public void setTop_head(Coordinate top_head) {
                this.top_head = top_head;
            }

            public Coordinate getNeck() {
                return neck;
            }

            public void setNeck(Coordinate neck) {
                this.neck = neck;
            }

            public Coordinate getRight_ear() {
                return right_ear;
            }

            public void setRight_ear(Coordinate right_ear) {
                this.right_ear = right_ear;
            }

            public Coordinate getLeft_ear() {
                return left_ear;
            }

            public void setLeft_ear(Coordinate left_ear) {
                this.left_ear = left_ear;
            }

            public Coordinate getLeft_elbow() {
                return left_elbow;
            }

            public void setLeft_elbow(Coordinate left_elbow) {
                this.left_elbow = left_elbow;
            }

            public Coordinate getRight_shoulder() {
                return right_shoulder;
            }

            public void setRight_shoulder(Coordinate right_shoulder) {
                this.right_shoulder = right_shoulder;
            }

            public Coordinate getRight_mouth_corner() {
                return right_mouth_corner;
            }

            public void setRight_mouth_corner(Coordinate right_mouth_corner) {
                this.right_mouth_corner = right_mouth_corner;
            }

            public Coordinate getRight_eye() {
                return right_eye;
            }

            public void setRight_eye(Coordinate right_eye) {
                this.right_eye = right_eye;
            }

            public Coordinate getLeft_ankle() {
                return left_ankle;
            }

            public void setLeft_ankle(Coordinate left_ankle) {
                this.left_ankle = left_ankle;
            }

            public Coordinate getRight_hip() {
                return right_hip;
            }

            public void setRight_hip(Coordinate right_hip) {
                this.right_hip = right_hip;
            }

            public Coordinate getLeft_wrist() {
                return left_wrist;
            }

            public void setLeft_wrist(Coordinate left_wrist) {
                this.left_wrist = left_wrist;
            }

            public Coordinate getLeft_shoulder() {
                return left_shoulder;
            }

            public void setLeft_shoulder(Coordinate left_shoulder) {
                this.left_shoulder = left_shoulder;
            }
        }
    }
}
