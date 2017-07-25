var gulp = require('gulp'),
    cleanCss = require('gulp-clean-css'),
    uglyflyJs = require('gulp-uglyfly'),
    using = require('gulp-using');

gulp.task('minify', function(){
    copyAllFiles();
});

function minimizeJs() {
    gulp.src([
        'src/main/webapp/assets/js/**/*.js'
    ])
        .pipe(using({}))
        .pipe(uglyflyJs().on('error', console.log))
        .pipe(gulp.dest('src/main/webapp/assets/js'));
}

function minimizeCss() {
    gulp.src([
        'src/main/webapp/assets/css/**/*.css'
    ])
        .pipe(using({}))
        .pipe(cleanCss().on('error', console.log))
        .pipe(gulp.dest('src/main/webapp/assets/css'));
}

function copyAllFiles() {
    gulp.src([
        'src/main/webapp/resources/**/*.*'
    ])
        .pipe(using({}))
        .pipe(gulp.dest('src/main/webapp/assets/'))
        .on('end', function (){
            minimizeCss();
            minimizeJs();
        });
}