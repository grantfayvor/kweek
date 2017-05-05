module.exports = function (grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        concat: {
            options: {
                separator: ';'
            },
            dist: {
                src: ['app/angular.js',
                    'app/angular-cookies.js',
                    'node_modules/angular-ui-router/release/angular-ui-router.js',
                    // 'node_modules/angular-ui-router/src/*.js',
                    'app/config/*.js',
                    'app/service/*.js',
                    'app/main.js',
                    'app/modules/**/*.js'],
                dest: 'app/compiled/js/<%= pkg.name %>.js'
            }
        },
        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> <%= grunt.template.today() %> */\n'
            },
            dist: {
                files: {
                    'app/compiled/js/<%= pkg.name %>.min.js': ['<%= concat.dist.dest %>']
                }
            }
        },
        jshint: {
            files: ['Gruntfile.js',
                'app/config/*.js',
                'app/modules/**/*.js',
                'app/service/*.js',
                'app/main.js'],
            options: {
                globals: {
                    jQuery: true,
                }
            }
        },
        cssmin: {
            options: {
                roundingPrecision: -1,
                shorthandCompacting: true,
                keepSpecialComments: 0
            },
            target: {
                files: {
                    'app/compiled/css/<%= pkg.name %>.min.css': [
                        'build/css/*.css',
                        'css/*.css',
                        'test/**/*.css'
                    ]
                }
            }
        },
        watch: {
            files: ['<%= jshint.files %>'],
            tasks: ['jshint', 'concat', 'uglify', 'cssmin']
        }

    });

    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-cssmin');


    grunt.registerTask('default', ['jshint', 'concat', 'uglify', 'cssmin', 'watch']);

};