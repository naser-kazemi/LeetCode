//
// Created by Naser Kazemi on 10/10/2021 AD.
//
#include <iostream>
#include <unordered_map>

using namespace std;

class Problem {
public:
    int passing;
    int distance;
    int starting;

    Problem(int passing, int distance, int starting) {
        this->passing = passing;
        this->distance = distance;
        this->starting = starting;
    }

    bool operator==(Problem other) const {
        return this->passing == other.passing && this->distance == other.distance && this->starting == other.starting;
    }
};

namespace std {

    template<>
    struct hash<Problem> {
        std::size_t operator()(const Problem &p) const {
            using std::size_t;
            using std::hash;
            using std::string;
            return ((hash<int>()(p.passing)
                     ^ (hash<int>()(p.distance) << 1)) >> 1)
                   ^ (hash<int>()(p.starting) << 1);
        }
    };

}

static unordered_map<Problem, double> memory;

double probability(int passing, int distance, int starting) {
    Problem problem = Problem(passing, distance, starting);
    if (memory.find(problem) != memory.end())
        return memory[problem];

    if (starting < 0)
        return 0;
    if (distance == 0) {
        if (passing == starting)
            return 1;
        else
            return 0;
    }

    double p = 0.5 * probability(passing, distance - 1, starting) +
               0.5 * probability(passing, distance - 1, starting - 1);
    memory[problem] = p;
    return p;
}


//int main() {
//    cout << probability(0, 6, 4);
//    return 0;
//}
